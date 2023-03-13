package com.cosmin.hotels.infrastructure.storage.converters;

import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;
import com.cosmin.hotels.infrastructure.storage.documents.HotelAvailabilitySearchDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelAvailabilitySearchToHotelAvailabilitySearchDocumentConverter implements
        Converter<HotelAvailabilitySearch, HotelAvailabilitySearchDocument> {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchToHotelAvailabilitySearchDocumentConverter.class);

    @Override
    @NonNull
    public HotelAvailabilitySearchDocument convert(HotelAvailabilitySearch source) {
        LOG.info("Converting HotelAvailabilitySearch '{}' to HotelAvailabilitySearchDocument.", source);
        return new HotelAvailabilitySearchDocument.Builder()
                .searchId(source.getSearchId())
                .hotelId(source.getHotelId())
                .checkIn(source.getCheckIn())
                .checkOut(source.getCheckOut())
                .ages(source.getAges())
                .build();
    }
}
