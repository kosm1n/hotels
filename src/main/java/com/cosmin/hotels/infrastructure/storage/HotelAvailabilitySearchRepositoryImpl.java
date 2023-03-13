package com.cosmin.hotels.infrastructure.storage;

import com.cosmin.hotels.domain.handler.HotelsNotFoundException;
import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;
import com.cosmin.hotels.domain.repositories.HotelAvailabilitySearchRepository;
import com.cosmin.hotels.infrastructure.storage.converters.HotelAvailabilitySearchDocumentToHotelAvailabilitySearchConverter;
import com.cosmin.hotels.infrastructure.storage.converters.HotelAvailabilitySearchToHotelAvailabilitySearchDocumentConverter;
import com.cosmin.hotels.infrastructure.storage.documents.HotelAvailabilitySearchDocument;
import com.cosmin.hotels.infrastructure.storage.repositories.HotelAvailabilitySearchDocumentRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HotelAvailabilitySearchRepositoryImpl implements HotelAvailabilitySearchRepository {

    private HotelAvailabilitySearchDocumentRepository repository;
    private HotelAvailabilitySearchToHotelAvailabilitySearchDocumentConverter toHotelAvailabilitySearchDocumentConverter;
    private HotelAvailabilitySearchDocumentToHotelAvailabilitySearchConverter toHotelAvailabilitySearchConverter;

    public HotelAvailabilitySearchRepositoryImpl(
            HotelAvailabilitySearchDocumentRepository repository,
            HotelAvailabilitySearchToHotelAvailabilitySearchDocumentConverter toHotelAvailabilitySearchDocumentConverter,
            HotelAvailabilitySearchDocumentToHotelAvailabilitySearchConverter toHotelAvailabilitySearchConverter) {
        this.repository = repository;
        this.toHotelAvailabilitySearchDocumentConverter = toHotelAvailabilitySearchDocumentConverter;
        this.toHotelAvailabilitySearchConverter = toHotelAvailabilitySearchConverter;
    }

    @Override
    public HotelAvailabilitySearch findBySearchId(String searchId) {
        return toHotelAvailabilitySearchConverter.convert(
                repository.findBySearchId(searchId)
                        .orElseThrow(() -> new HotelsNotFoundException("Document not found with searchId: "+searchId)));
    }

    @Override
    public HotelAvailabilitySearch save(
            HotelAvailabilitySearch hotelAvailabilitySearch) {
        return toHotelAvailabilitySearchConverter.convert(
                repository.save(toHotelAvailabilitySearchDocumentConverter.convert(hotelAvailabilitySearch)));
    }

    @Override
    public Integer countAllBy(
            String hotelId, LocalDate checkIn, LocalDate checkOut, Integer[] ages) {
        final List<HotelAvailabilitySearch> hotelAvailabilitySearches = new ArrayList<>();
        final List<HotelAvailabilitySearchDocument> hotelAvailabilitySearchDocumentList =
                repository.findAllBy(hotelId, checkIn, checkOut, ages);

        return CollectionUtils.isEmpty(hotelAvailabilitySearchDocumentList) ? 0: hotelAvailabilitySearchDocumentList.size();
    }

}
