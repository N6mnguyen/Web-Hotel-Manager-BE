package com.codeWithProject.hotelServer.admin.rooms.mapper.admin.rooms;

import com.codeWithProject.hotelServer.dto.RoomDto;
import com.codeWithProject.hotelServer.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    Room toRoom (RoomDto roomDto);

//    void  updateRoom(@MappingTarget Room room,RoomDto roomDto);

}
