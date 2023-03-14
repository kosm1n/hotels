package com.cosmin.hotels.infrastructure.storage;

import com.cosmin.hotels.domain.handler.HotelsNotFoundException;
import com.cosmin.hotels.domain.model.Hotel;
import com.cosmin.hotels.domain.repositories.HotelRepository;
import com.cosmin.hotels.infrastructure.storage.converters.HotelDocumentToHotelConverter;
import com.cosmin.hotels.infrastructure.storage.converters.HotelToHotelDocumentConverter;
import com.cosmin.hotels.infrastructure.storage.documents.HotelDocument;
import com.cosmin.hotels.infrastructure.storage.repositories.HotelDocumentRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HotelRepositoryImpl implements HotelRepository {

    private HotelDocumentRepository repository;
    private HotelToHotelDocumentConverter toHotelAvailabilitySearchDocumentConverter;
    private HotelDocumentToHotelConverter toHotelAvailabilitySearchConverter;

    public HotelRepositoryImpl(
            HotelDocumentRepository repository,
            HotelToHotelDocumentConverter toHotelAvailabilitySearchDocumentConverter,
            HotelDocumentToHotelConverter toHotelAvailabilitySearchConverter) {
        this.repository = repository;
        this.toHotelAvailabilitySearchDocumentConverter = toHotelAvailabilitySearchDocumentConverter;
        this.toHotelAvailabilitySearchConverter = toHotelAvailabilitySearchConverter;
    }

    @Override
    public Hotel findBySearchId(String searchId) {
        return toHotelAvailabilitySearchConverter.convert(
                repository.findBySearchId(searchId)
                        .orElseThrow(() -> new HotelsNotFoundException("Document not found with searchId: "+searchId)));
    }

    @Override
    public Hotel save(
            Hotel hotel) {
        return toHotelAvailabilitySearchConverter.convert(
                repository.save(toHotelAvailabilitySearchDocumentConverter.convert(hotel)));
    }

    @Override
    public Integer countAllBy(
            String hotelId, LocalDate checkIn, LocalDate checkOut, Integer[] ages) {
        final List<Hotel> hotels = new ArrayList<>();
        final List<HotelDocument> hotelDocumentList =
                repository.findAllBy(hotelId, checkIn, checkOut, ages);

        return CollectionUtils.isEmpty(hotelDocumentList) ? 0: hotelDocumentList.size();
    }

}
