package com.project.hotel.service.controllers;

import com.project.hotel.service.models.Hotel;
import com.project.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/create")
    public void registerHotel(@RequestBody Hotel hotel) {
        hotelService.registerNewHotel(hotel);
    }

    @GetMapping("/gethotel/{id}")
    public Hotel getHotelInfo(@PathVariable Integer id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("/allhotels")
    public List<Hotel> getAllHotels() {
        return hotelService.getHotelsList();
    }
}
