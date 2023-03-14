package com.cosmin.hotels.domain.services;

import com.cosmin.hotels.domain.model.Hotel;

public interface HotelService {

    Hotel sendHotelAvailabilitySearch(Hotel hotel);

    Hotel countHotelAvailabilitySearch(String searchId);

    Hotel saveHotelAvailabilitySearchEvent(Hotel hotel);

}
