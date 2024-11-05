package com.codeWithProject.hotelServer.services.customer.booking;

import com.codeWithProject.hotelServer.dto.ReservationDto;
import com.codeWithProject.hotelServer.dto.ReservationResponseDto;
import com.codeWithProject.hotelServer.entity.Reservation;
import com.codeWithProject.hotelServer.entity.Room;
import com.codeWithProject.hotelServer.entity.User;
import com.codeWithProject.hotelServer.enums.ReservationStatus;
import com.codeWithProject.hotelServer.repository.ReservationRepository;
import com.codeWithProject.hotelServer.repository.RoomRepository;
import com.codeWithProject.hotelServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final UserRepository userRepository;     
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private static final int SEARCH_RESULT_PER_PAGE =4;
    public boolean postReservation(ReservationDto reservationDto) {

        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());
        if (optionalRoom.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setRoom(optionalRoom.get());
            reservation.setUser(optionalUser.get());
            reservation.setReservationStatus(ReservationStatus.FENDING);
            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRoom.get().getPrice() * days);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }
    public ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,SEARCH_RESULT_PER_PAGE);
        Page<Reservation> reservationPage = reservationRepository.findAllByUserId(pageable,userId);
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        // Sử dụng stream để duyệt qua từng Reservation và chuyển đổi nó thành ReservationDto.
        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto)
                .collect(Collectors.toList()));
        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPage(reservationPage.getTotalPages());
        return  reservationResponseDto;
    }
}
