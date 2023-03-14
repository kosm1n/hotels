package com.cosmin.hotels.application.converters;

import com.cosmin.hotels.application.dto.HotelDto;
import com.cosmin.hotels.domain.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelDtoToHotelConverter implements
        Converter<HotelDto, Hotel> {

    final Logger LOG = LoggerFactory.getLogger(HotelDtoToHotelConverter.class);

    @Override
    @NonNull
    public Hotel convert(HotelDto source) {
        LOG.info("Converting HotelDto '{}' to Hotel.", source);
        return new Hotel.Builder()
                .hotelId(source.getHotelId())
                .checkIn(source.getCheckIn())
                .checkOut(source.getCheckOut())
                .ages(source.getAges())
                .build();
    }
}
