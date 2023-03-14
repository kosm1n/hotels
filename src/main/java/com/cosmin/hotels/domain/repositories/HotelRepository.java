package com.cosmin.hotels.domain.repositories;

import com.cosmin.hotels.domain.model.Hotel;

import java.time.LocalDate;

public interface HotelRepository {

    Hotel findBySearchId(String searchId);

    Integer countAllBy(
            String hotelId, LocalDate checkIn, LocalDate checkOut, Integer[] ages);

    Hotel save(
            Hotel hotel);

}
