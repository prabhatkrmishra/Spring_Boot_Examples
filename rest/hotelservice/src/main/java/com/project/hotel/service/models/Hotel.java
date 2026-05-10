package com.project.hotel.service.models;

import jakarta.validation.constraints.*;

public class Hotel {
    private Integer hotelId;

    @NotNull
    @Size(min = 3, max = 20)
    private String hotelName;

    @NotNull
    private String hotelCity;

    @NotNull
    private String hotelDescription;

    @Min(value = 1)
    @Max(value = 10)
    private short hotelRating;

    private Owner owner;

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

    public void setHotelRating(short hotelRating) {
        this.hotelRating = hotelRating;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void printDetails() {
        System.out.println(hotelId + " | " + hotelName + " | " + hotelCity + " | " + hotelDescription + " | " + hotelRating);
    }
}
