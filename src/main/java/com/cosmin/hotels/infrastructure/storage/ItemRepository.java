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
    
    @Query(value="{hotelId:'?0'}")
    List<HotelAvailabilitySearchDocument> findAllByHotelIdAndCheckIn(String hotelId, LocalDate checkIn);
    
    public long count();

}