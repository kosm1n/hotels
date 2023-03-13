package com.cosmin.hotels.infrastructure.topics.converters;

import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;
import com.cosmin.hotels.infrastructure.topics.dto.HotelAvailabilitySearchEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelAvailabilitySearchToHotelAvailabilitySearchEventConverter implements
        Converter<HotelAvailabilitySearch, HotelAvailabilitySearchEvent> {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchToHotelAvailabilitySearchEventConverter.class);

    @Override
    @NonNull
    public HotelAvailabilitySearchEvent convert(HotelAvailabilitySearch source) {
        LOG.info("Converting HotelAvailabilitySearch '{}' to HotelAvailabilitySearchEvent.", source);
        return new HotelAvailabilitySearchEvent.Builder()
                .searchId(source.getSearchId())
                .hotelId(source.getHotelId())
                .checkIn(source.getCheckIn())
                .checkOut(source.getCheckOut())
                .ages(source.getAges())
                .build();
    }
}
