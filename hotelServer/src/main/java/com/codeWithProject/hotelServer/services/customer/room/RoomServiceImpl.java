package com.codeWithProject.hotelServer.services.customer.room;

import com.codeWithProject.hotelServer.dto.RoomDto;
import com.codeWithProject.hotelServer.dto.RoomsResponseDto;
import com.codeWithProject.hotelServer.entity.Room;
import com.codeWithProject.hotelServer.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;


    public RoomsResponseDto getAvailableRooms(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Room> roomPage = roomRepository.findByAvailable(true, pageable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomsResponseDto.setTotalPages(roomPage.getTotalPages());
        roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
        return roomsResponseDto;
    }
    public RoomDto getRoomById(Long id ){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if(optionalRoom.isPresent()){
            return optionalRoom.get().getRoomDto();
        }
        else {
            throw new EntityNotFoundException("Room not present");
        }
    }

    @Override
    public Page<Room> searchRoomsByName(String name, Pageable pageable) {
        if (name == null || name.trim().isEmpty()) {
            // Nếu không có từ khóa tìm kiếm, trả về trang đầu tiên với tất cả các phòng
            return roomRepository.findAll(pageable);
        }
        // Tìm kiếm phòng theo tên (nếu có từ khóa)
        return roomRepository.findByName(name, pageable);
    }
}
