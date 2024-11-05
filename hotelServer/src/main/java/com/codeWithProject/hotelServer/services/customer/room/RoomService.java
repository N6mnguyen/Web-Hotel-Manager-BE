package com.codeWithProject.hotelServer.services.customer.room;

import com.codeWithProject.hotelServer.dto.RoomDto;
import com.codeWithProject.hotelServer.dto.RoomsResponseDto;
import com.codeWithProject.hotelServer.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RoomService {

    RoomsResponseDto  getAvailableRooms (int pageNumber);

    RoomDto getRoomById(Long id);

//    List<Room> searchRoomsByName(String keyword);

    Page<Room> searchRoomsByName(String name, Pageable pageable) ;
}
