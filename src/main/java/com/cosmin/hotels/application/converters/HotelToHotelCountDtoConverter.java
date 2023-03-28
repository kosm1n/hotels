package com.cosmin.hotels.application.converters;

import com.cosmin.hotels.application.dto.HotelCountDto;
import com.cosmin.hotels.application.dto.HotelDto;
import com.cosmin.hotels.domain.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelToHotelCountDtoConverter implements
        Converter<Hotel, HotelCountDto> {

    final Logger LOG = LoggerFactory.getLogger(HotelToHotelCountDtoConverter.class);

    @Override
    @NonNull
    public HotelCountDto convert(final Hotel source) {
        LOG.info("Converting Hotel '{}' to HotelCountDto.", source);
        return new HotelCountDto.Builder()
                .count(source.getCount())
                .search(new HotelDto.Builder()
                        .hotelId(source.getHotelId())
                        .checkIn(source.getCheckIn())
                        .checkOut(source.getCheckOut())
                        .ages(source.getAges())
                        .build())
                .searchId(source.getSearchId())
                .build();
    }
}
