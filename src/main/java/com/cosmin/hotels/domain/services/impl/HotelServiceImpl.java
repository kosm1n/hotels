package com.cosmin.hotels.domain.services.impl;

import com.cosmin.hotels.domain.model.Hotel;
import com.cosmin.hotels.domain.repositories.HotelEventRepository;
import com.cosmin.hotels.domain.repositories.HotelRepository;
import com.cosmin.hotels.domain.services.HotelService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {

    final Logger LOG = LoggerFactory.getLogger(HotelServiceImpl.class);

    private HotelRepository hotelRepository;
    private HotelEventRepository hotelEventRepository;

    @Autowired
    public HotelServiceImpl(
            HotelRepository hotelRepository,
            HotelEventRepository hotelEventRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelEventRepository = hotelEventRepository;
    }

    @Override
    public Hotel sendHotelAvailabilitySearch(Hotel hotel) {
        LOG.info("HotelAvailabilitySearchServiceImpl, Send Hotel-Availability-Search {}", hotel);
        hotel.setSearchId(RandomStringUtils.randomAlphanumeric(5));
        hotelEventRepository.send("hotel-availability-searches", hotel);
        return hotel;
    }

    @Override
    public Hotel countHotelAvailabilitySearch(String searchId) {
        LOG.info("HotelAvailabilitySearchServiceImpl, Count Hotel-Availability-Search {}", searchId);
        Hotel hotel = hotelRepository.findBySearchId(searchId);
        hotel.setCount(
                hotelRepository.countAllBy(
                        hotel.getHotelId(),
                        hotel.getCheckIn(),
                        hotel.getCheckOut(),
                        hotel.getAges()));
        return hotel;
    }

    @Override
    public Hotel saveHotelAvailabilitySearchEvent(Hotel hotel) {
        LOG.info("HotelAvailabilitySearchServiceImpl, Save Hotel-Availability-Search {}", hotel);

        return hotelRepository.save(hotel);
    }

}
