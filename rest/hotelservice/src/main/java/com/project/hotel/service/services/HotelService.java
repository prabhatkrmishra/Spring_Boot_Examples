package com.project.hotel.service.services;

import com.project.hotel.service.models.Hotel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelService {
    private final List<Hotel> hotelList = new ArrayList<>();
    private final Map<Integer, Hotel> hotelMap = new HashMap<>();

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public Map<Integer, Hotel> getHotelMap() {
        return hotelMap;
    }

    public void registerNewHotel(Hotel hotel) {
        hotelList.add(hotel);
        System.out.println(hotel.getHotelId() + " | " + hotel.getHotelName() + " | " + hotel.getHotelCity() + " | " + hotel.getHotelDescription() + " | " + hotel.getHotelRating());
        hotelMap.put(hotel.getHotelId(), hotel);
    }

    public Hotel getHotelById(Integer id) {
        return hotelMap.get(id);
    }

    public List<Hotel> getHotelsList() {
        return getHotelList();
    }
}
