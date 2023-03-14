package com.cosmin.hotels.infrastructure.storage.converters;

import com.cosmin.hotels.domain.model.Hotel;
import com.cosmin.hotels.infrastructure.storage.documents.HotelDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelToHotelDocumentConverter implements
        Converter<Hotel, HotelDocument> {

    final Logger LOG = LoggerFactory.getLogger(HotelToHotelDocumentConverter.class);

    @Override
    @NonNull
    public HotelDocument convert(Hotel source) {
        LOG.info("Converting Hotel '{}' to HotelDocument.", source);
        return new HotelDocument.Builder()
                .searchId(source.getSearchId())
                .hotelId(source.getHotelId())
                .checkIn(source.getCheckIn())
                .checkOut(source.getCheckOut())
                .ages(source.getAges())
                .build();
    }
}
