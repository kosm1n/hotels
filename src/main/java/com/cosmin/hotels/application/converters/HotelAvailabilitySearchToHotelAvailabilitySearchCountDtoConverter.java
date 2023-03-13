package com.cosmin.hotels.application.converters;

import com.cosmin.hotels.application.dto.HotelAvailabilitySearchCountDto;
import com.cosmin.hotels.application.dto.HotelAvailabilitySearchDto;
import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelAvailabilitySearchToHotelAvailabilitySearchCountDtoConverter implements
        Converter<HotelAvailabilitySearch, HotelAvailabilitySearchCountDto> {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchToHotelAvailabilitySearchCountDtoConverter.class);

    @Override
    @NonNull
    public HotelAvailabilitySearchCountDto convert(HotelAvailabilitySearch source) {
        LOG.info("Converting HotelAvailabilitySearch '{}' to HotelAvailabilitySearchCountDto.", source);
        return new HotelAvailabilitySearchCountDto.Builder()
                .count(source.getCount())
                .search(new HotelAvailabilitySearchDto.Builder()
                        .hotelId(source.getHotelId())
                        .checkIn(source.getCheckIn())
                        .checkOut(source.getCheckOut())
                        .ages(source.getAges())
                        .build())
                .searchId(source.getSearchId())
                .build();
    }
}
