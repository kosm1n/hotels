package com.cosmin.hotels.infrastructure.topics.converters;

import com.cosmin.hotels.domain.model.Hotel;
import com.cosmin.hotels.infrastructure.topics.dto.HotelEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelEventToHotelConverter implements
        Converter<HotelEvent, Hotel> {

    final Logger LOG = LoggerFactory.getLogger(HotelEventToHotelConverter.class);

    @Override
    @NonNull
    public Hotel convert(HotelEvent source) {
        LOG.info("Converting HotelEvent '{}' to Hotel.", source);
        return new Hotel.Builder()
                .searchId(source.getSearchId())
                .hotelId(source.getHotelId())
                .checkIn(source.getCheckIn())
                .checkOut(source.getCheckOut())
                .ages(source.getAges())
                .build();
    }
}
