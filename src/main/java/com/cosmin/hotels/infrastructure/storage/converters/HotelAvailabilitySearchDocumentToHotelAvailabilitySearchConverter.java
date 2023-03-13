package com.cosmin.hotels.infrastructure.storage.converters;

import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;
import com.cosmin.hotels.infrastructure.storage.documents.HotelAvailabilitySearchDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelAvailabilitySearchDocumentToHotelAvailabilitySearchConverter implements
        Converter<HotelAvailabilitySearchDocument, HotelAvailabilitySearch> {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchDocumentToHotelAvailabilitySearchConverter.class);

    @Override
    @NonNull
    public HotelAvailabilitySearch convert(HotelAvailabilitySearchDocument source) {
        LOG.info("Converting HotelAvailabilitySearchDocument '{}' to HotelAvailabilitySearch.", source);
        return new HotelAvailabilitySearch.Builder()
                .searchId(source.getSearchId())
                .hotelId(source.getHotelId())
                .checkIn(source.getCheckInDate())
                .checkOut(source.getCheckOutDate())
                .ages(source.getAges())
                .build();
    }
}
