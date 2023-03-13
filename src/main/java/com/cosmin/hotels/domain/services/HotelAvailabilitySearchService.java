package com.cosmin.hotels.domain.services;

import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;

public interface HotelAvailabilitySearchService {

    HotelAvailabilitySearch sendHotelAvailabilitySearch(HotelAvailabilitySearch hotelAvailabilitySearch);

    HotelAvailabilitySearch countHotelAvailabilitySearch(String searchId);

    HotelAvailabilitySearch saveHotelAvailabilitySearchEvent(HotelAvailabilitySearch hotelAvailabilitySearch);

}
