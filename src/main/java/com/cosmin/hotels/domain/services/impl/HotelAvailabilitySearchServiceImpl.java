package com.cosmin.hotels.domain.services.impl;

import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;
import com.cosmin.hotels.domain.repositories.HotelAvailabilitySearchEventRepository;
import com.cosmin.hotels.domain.repositories.HotelAvailabilitySearchRepository;
import com.cosmin.hotels.domain.services.HotelAvailabilitySearchService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelAvailabilitySearchServiceImpl implements HotelAvailabilitySearchService {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchServiceImpl.class);

    private HotelAvailabilitySearchRepository hotelAvailabilitySearchRepository;
    private HotelAvailabilitySearchEventRepository hotelAvailabilitySearchEventRepository;

    @Autowired
    public HotelAvailabilitySearchServiceImpl(
            HotelAvailabilitySearchRepository hotelAvailabilitySearchRepository,
            HotelAvailabilitySearchEventRepository hotelAvailabilitySearchEventRepository) {
        this.hotelAvailabilitySearchRepository = hotelAvailabilitySearchRepository;
        this.hotelAvailabilitySearchEventRepository = hotelAvailabilitySearchEventRepository;
    }

    @Override
    public HotelAvailabilitySearch sendHotelAvailabilitySearch(HotelAvailabilitySearch hotelAvailabilitySearch) {
        LOG.info("HotelAvailabilitySearchServiceImpl, Send Hotel-Availability-Search {}", hotelAvailabilitySearch);
        hotelAvailabilitySearch.setSearchId(RandomStringUtils.randomAlphanumeric(5));
        hotelAvailabilitySearchEventRepository.send("hotel-availability-searches", hotelAvailabilitySearch);
        return hotelAvailabilitySearch;
    }

    @Override
    public HotelAvailabilitySearch countHotelAvailabilitySearch(String searchId) {
        LOG.info("HotelAvailabilitySearchServiceImpl, Count Hotel-Availability-Search {}", searchId);
        HotelAvailabilitySearch hotelAvailabilitySearch = hotelAvailabilitySearchRepository.findBySearchId(searchId);
        hotelAvailabilitySearch.setCount(
                hotelAvailabilitySearchRepository.countAllBy(
                        hotelAvailabilitySearch.getHotelId(),
                        hotelAvailabilitySearch.getCheckIn(),
                        hotelAvailabilitySearch.getCheckOut(),
                        hotelAvailabilitySearch.getAges()));
        return hotelAvailabilitySearch;
    }

    @Override
    public HotelAvailabilitySearch saveHotelAvailabilitySearchEvent(HotelAvailabilitySearch hotelAvailabilitySearch) {
        LOG.info("HotelAvailabilitySearchServiceImpl, Save Hotel-Availability-Search {}", hotelAvailabilitySearch);

        return hotelAvailabilitySearchRepository.save(hotelAvailabilitySearch);
    }

}
