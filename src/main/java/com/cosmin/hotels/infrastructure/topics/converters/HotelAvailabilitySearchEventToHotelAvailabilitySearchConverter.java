package com.cosmin.hotels.infrastructure.topics.converters;

import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;
import com.cosmin.hotels.infrastructure.topics.dto.HotelAvailabilitySearchEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HotelAvailabilitySearchEventToHotelAvailabilitySearchConverter implements
        Converter<HotelAvailabilitySearchEvent, HotelAvailabilitySearch> {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchEventToHotelAvailabilitySearchConverter.class);

    @Override
    @NonNull
    public HotelAvailabilitySearch convert(HotelAvailabilitySearchEvent source) {
        LOG.info("Converting HotelAvailabilitySearchEvent '{}' to HotelAvailabilitySearch.", source);
        return new HotelAvailabilitySearch.Builder()
                .searchId(source.getSearchId())
                .hotelId(source.getHotelId())
                .checkIn(source.getCheckIn())
                .checkOut(source.getCheckOut())
                .ages(source.getAges())
                .build();
    }
}
