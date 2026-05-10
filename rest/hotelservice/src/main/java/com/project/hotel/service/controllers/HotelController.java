package com.project.hotel.service.controllers;

import com.project.hotel.service.models.Hotel;
import com.project.hotel.service.services.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<String> registerHotel(
            @Valid @RequestBody Hotel hotel) {

        hotelService.registerNewHotel(hotel);

        return ResponseEntity.ok("Hotel registered successfully");
    }

    @GetMapping("/gethotel/{id}")
    public ResponseEntity<Hotel> getHotelInfo(@PathVariable Integer id) {

        Hotel hotel = hotelService.getHotelById(id);

        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/allhotels")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getHotelsList());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateHotel(@PathVariable Integer id,
                                              @RequestBody Hotel hotel) {

        hotelService.updateHotelDetails(id, hotel);

        return ResponseEntity.ok("Hotel with id: " + id + " updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deRegisterHotel(@PathVariable Integer id) {

        hotelService.deRegisterExistingHotel(id);

        return ResponseEntity.noContent().build();
    }
}