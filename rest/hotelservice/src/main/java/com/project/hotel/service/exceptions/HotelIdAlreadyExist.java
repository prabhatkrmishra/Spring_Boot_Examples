package com.project.hotel.service.exceptions;

public class HotelIdAlreadyExist extends RuntimeException {
    public HotelIdAlreadyExist(String message) {
        super(message);
    }
}
