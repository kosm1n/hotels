package com.cosmin.hotels.infrastructure.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<HotelAvailabilitySearchDocument, String> {
    
    @Query("{id:'?0'}")
    HotelAvailabilitySearchDocument findItemByName(String name);

    Optional<HotelAvailabilitySearchDocument> findItemBySearchId(String id);
    
    @Query("{'hotelId' : ?0, 'checkInDate': ?1, 'checkOutDate': ?2, 'ages': {$all : ?3}}")
    List<HotelAvailabilitySearchDocument> findAll(
            String hotelId, LocalDate checkIn, LocalDate checkOut, Integer[] ages);

}