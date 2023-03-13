package com.cosmin.hotels.infrastructure.storage.repositories;

import com.cosmin.hotels.infrastructure.storage.documents.HotelAvailabilitySearchDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HotelAvailabilitySearchDocumentRepository extends MongoRepository<HotelAvailabilitySearchDocument, String> {

    @Query("{'searchId' : ?0}")
    Optional<HotelAvailabilitySearchDocument> findBySearchId(String searchId);

    @Query("{'hotelId' : ?0, 'checkInDate': ?1, 'checkOutDate': ?2, 'ages': {$all : ?3}}")
    List<HotelAvailabilitySearchDocument> findAllBy(
            String hotelId, LocalDate checkIn, LocalDate checkOut, Integer[] ages);

}