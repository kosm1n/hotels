package com.cosmin.hotels.infrastructure.topics.converters;

import com.cosmin.hotels.domain.model.Hotel;
import com.cosmin.hotels.infrastructure.topics.dto.HotelEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelToHotelEventConverter implements
        Converter<Hotel, HotelEvent> {

    final Logger LOG = LoggerFactory.getLogger(HotelToHotelEventConverter.class);

    @Override
    @NonNull
    public HotelEvent convert(Hotel source) {
        LOG.info("Converting Hotel '{}' to HotelEvent.", source);
        return new HotelEvent.Builder()
                .searchId(source.getSearchId())
                .hotelId(source.getHotelId())
                .checkIn(source.getCheckIn())
                .checkOut(source.getCheckOut())
                .ages(source.getAges())
                .build();
    }
}
