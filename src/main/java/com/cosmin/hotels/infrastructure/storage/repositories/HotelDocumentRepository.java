package com.cosmin.hotels.infrastructure.storage.repositories;

import com.cosmin.hotels.infrastructure.storage.documents.HotelDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HotelDocumentRepository extends MongoRepository<HotelDocument, String> {

    @Query("{'searchId' : ?0}")
    Optional<HotelDocument> findBySearchId(String searchId);

    @Query("{'hotelId' : ?0, 'checkInDate': ?1, 'checkOutDate': ?2, 'ages': {$all : ?3}}")
    List<HotelDocument> findAllBy(
            String hotelId, LocalDate checkIn, LocalDate checkOut, Integer[] ages);

}