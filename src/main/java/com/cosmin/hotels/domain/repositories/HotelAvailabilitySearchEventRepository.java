package com.cosmin.hotels.domain.repositories;

import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;

public interface HotelAvailabilitySearchEventRepository {

    void send(String topic, HotelAvailabilitySearch payload);

}
