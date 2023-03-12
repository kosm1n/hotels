package com.cosmin.hotels.application;


import com.cosmin.hotels.application.dto.HotelAvailabilitySearchRequestDto;
import com.cosmin.hotels.application.dto.HotelAvailabilitySearchResponseDto;
import com.cosmin.hotels.infrastructure.topics.HotelAvailabilitySearchesProducer;
import com.cosmin.hotels.infrastructure.topics.dto.HotelAvailabilitySearchEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
public class HotelAvailabilitySearchesController {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchesController.class);

    private HotelAvailabilitySearchesProducer hotelAvailabilitySearchesProducer;

    @Autowired
    public HotelAvailabilitySearchesController(HotelAvailabilitySearchesProducer hotelAvailabilitySearchesProducer) {
        this.hotelAvailabilitySearchesProducer = hotelAvailabilitySearchesProducer;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<HotelAvailabilitySearchResponseDto> hotelAvailabilitySearch(@Valid @RequestBody HotelAvailabilitySearchRequestDto request) {
        LOG.info("HotelAvailabilitySearchesController - POST /search with RequestBody {}", request);

        final String searchId = RandomStringUtils.randomAlphanumeric(5);
        hotelAvailabilitySearchesProducer.send(
                "hotel-availability-searches",
                new HotelAvailabilitySearchEvent.Builder()
                        .searchId(searchId)
                        .hotelId(request.getHotelId())
                        .checkIn(request.getCheckIn())
                        .checkOut(request.getCheckOut())
                        .ages(request.getAges())
                        .build());

        return ResponseEntity.ok(new HotelAvailabilitySearchResponseDto.Builder().hotelId(searchId).build());
    }

    @GetMapping(value = "/count/{searchId}")
    public ResponseEntity<String> get(@PathVariable(name = "searchId") String searchId) {
        return ResponseEntity.ok("hi buddy: "+searchId);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> test(@RequestParam(name = "date") LocalDate date) {
        return ResponseEntity.ok("hi buddy: "+date);
    }

}
