package com.codeWithProject.hotelServer.dto;

import lombok.Data;

@Data
public class RoomDto {

    private Long id;

    private String name;

    private String type;

    private String image;

    private Long price;

    private boolean available;

}
