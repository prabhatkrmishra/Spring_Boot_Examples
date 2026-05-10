package com.project.hotel.service.services;

import com.project.hotel.service.models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * With exceptions:

 *  service → throws failure
 *  global handler → converts to HTTP response
 *  controller → only handles success path
 */

import com.project.hotel.service.exceptions.HotelNotFoundException;

import java.util.*;

@Service
public class HotelService {
    private final List<Hotel> hotelList = new ArrayList<>();
    private final Map<Integer, Hotel> hotelMap = new HashMap<>();
    public int fetchId = 1;

    @Autowired
    private OwnerService ownerService;

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public Map<Integer, Hotel> getHotelMap() {
        return hotelMap;
    }

    public void registerNewHotel(Hotel hotel) {
        hotel.printDetails();
        hotel.setOwner(ownerService.addOwnerDetails(fetchId++));
        hotelList.add(hotel);
        hotelMap.put(hotel.getHotelId(), hotel);
    }

    public Hotel getHotelById(Integer id) {

        if (!hotelMap.containsKey(id)) {
            throw new HotelNotFoundException("Hotel with id: " + id + " not found");
        }

        return hotelMap.get(id);
    }

    public List<Hotel> getHotelsList() {
        return getHotelList();
    }

    public void updateHotelDetails(Integer id, Hotel updatedHotel) {

        if (!hotelMap.containsKey(id)) {
            throw new HotelNotFoundException("Hotel with id: " + id + " not found");
        }

        Hotel oldHotel = hotelMap.get(id);
        int index = hotelList.indexOf(oldHotel);

        updatedHotel.setHotelId(id);

        hotelList.set(index, updatedHotel);
        hotelMap.put(id, updatedHotel);
    }

    /*
    //Useful for patch
    public void updateHotelDetails(Integer id, Hotel updatedHotel) {

        if (!hotelMap.containsKey(id)) {
            throw new HotelNotFoundException("Hotel with id: " + id + " not found");
        }

        Hotel existingHotel = hotelMap.get(id);

        existingHotel.setHotelName(updatedHotel.getHotelName());
        existingHotel.setHotelCity(updatedHotel.getHotelCity());
        existingHotel.setHotelDescription(updatedHotel.getHotelDescription());
        existingHotel.setHotelRating(updatedHotel.getHotelRating());
    }*/

    public void deRegisterExistingHotel(Integer id) {

        if (!hotelMap.containsKey(id)) {
            throw new HotelNotFoundException("Hotel with id: " + id + " not found");
        }

        hotelList.remove(hotelMap.get(id));
        hotelMap.remove(id);
    }
}