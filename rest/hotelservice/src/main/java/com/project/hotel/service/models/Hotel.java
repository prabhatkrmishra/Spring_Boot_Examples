package com.project.hotel.service.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class Hotel {
    private Integer hotelId;

    @Size(min = 3, max = 20)
    private String hotelName;
    private String hotelCity;
    private String hotelDescription;

    @Min(value = 1)
    @Max(value = 10)
    private short hotelRating;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public Short getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(Short hotelRating) {
        this.hotelRating = hotelRating;
    }
}
