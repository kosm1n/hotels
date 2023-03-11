package com.cosmin.hotels.application;


import com.cosmin.hotels.application.dto.HotelAvailabilitySearchRequestDto;
import com.cosmin.hotels.application.dto.HotelAvailabilitySearchResponseDto;
import com.cosmin.hotels.infrastructure.topics.HotelAvailabilitySearchesProducer;
import com.cosmin.hotels.infrastructure.topics.dto.HotelAvailabilitySearchEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelAvailabilitySearchesController {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchesController.class);

    private HotelAvailabilitySearchesProducer hotelAvailabilitySearchesProducer;

    @Autowired
    public HotelAvailabilitySearchesController(HotelAvailabilitySearchesProducer hotelAvailabilitySearchesProducer) {
        this.hotelAvailabilitySearchesProducer = hotelAvailabilitySearchesProducer;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<HotelAvailabilitySearchResponseDto> hotelAvailabilitySearch(@Validated @RequestBody HotelAvailabilitySearchRequestDto request) {
        LOG.info("HotelAvailabilitySearchesController - POST /search with RequestBody {}", request);
        hotelAvailabilitySearchesProducer.send(
                "hotel-availability-searches",
                new HotelAvailabilitySearchEvent.Builder()
                        .searchId(""+request.hashCode())
                        .hotelId(request.getHotelId())
                        .checkIn(request.getCheckIn())
                        .checkOut(request.getCheckOut())
                        .ages(request.getAges())
                        .build());
        return ResponseEntity.ok(new HotelAvailabilitySearchResponseDto.Builder().hotelId(""+request.hashCode()).build());
    }

    @GetMapping(value = "/count/{searchId}")
    public ResponseEntity<String> get(@PathVariable(name = "searchId") String searchId) {
        return ResponseEntity.ok("hi buddy: "+searchId);
    }

}
