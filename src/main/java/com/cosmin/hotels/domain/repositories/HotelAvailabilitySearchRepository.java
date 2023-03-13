package com.cosmin.hotels.domain.repositories;

import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;

import java.time.LocalDate;
import java.util.List;

public interface HotelAvailabilitySearchRepository {

    HotelAvailabilitySearch findBySearchId(String searchId);

    List<HotelAvailabilitySearch> findAllBy(
            String hotelId, LocalDate checkIn, LocalDate checkOut, Integer[] ages);

    HotelAvailabilitySearch save(
            HotelAvailabilitySearch hotelAvailabilitySearch);

}
