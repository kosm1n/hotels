package com.cosmin.hotels.domain.repositories;

import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;

import java.time.LocalDate;

public interface HotelAvailabilitySearchRepository {

    HotelAvailabilitySearch findBySearchId(String searchId);

    Integer countAllBy(
            String hotelId, LocalDate checkIn, LocalDate checkOut, Integer[] ages);

    HotelAvailabilitySearch save(
            HotelAvailabilitySearch hotelAvailabilitySearch);

}
