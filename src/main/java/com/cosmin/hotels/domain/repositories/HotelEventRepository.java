package com.cosmin.hotels.domain.repositories;

import com.cosmin.hotels.domain.model.Hotel;

public interface HotelEventRepository {

    void send(String topic, Hotel payload);

}
