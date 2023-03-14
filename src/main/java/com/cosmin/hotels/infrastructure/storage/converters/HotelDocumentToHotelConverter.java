package com.cosmin.hotels.infrastructure.storage.converters;

import com.cosmin.hotels.domain.model.Hotel;
import com.cosmin.hotels.infrastructure.storage.documents.HotelDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelDocumentToHotelConverter implements
        Converter<HotelDocument, Hotel> {

    final Logger LOG = LoggerFactory.getLogger(HotelDocumentToHotelConverter.class);

    @Override
    @NonNull
    public Hotel convert(HotelDocument source) {
        LOG.info("Converting HotelDocument '{}' to Hotel.", source);
        return new Hotel.Builder()
                .searchId(source.getSearchId())
                .hotelId(source.getHotelId())
                .checkIn(source.getCheckInDate())
                .checkOut(source.getCheckOutDate())
                .ages(source.getAges())
                .build();
    }
}
