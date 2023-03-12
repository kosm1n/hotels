package com.cosmin.hotels.application;


import com.cosmin.hotels.application.dto.CountResponseDto;
import com.cosmin.hotels.application.dto.HotelAvailabilitySearchRequestDto;
import com.cosmin.hotels.application.dto.HotelAvailabilitySearchResponseDto;
import com.cosmin.hotels.infrastructure.storage.HotelAvailabilitySearchDocument;
import com.cosmin.hotels.infrastructure.storage.ItemRepository;
import com.cosmin.hotels.infrastructure.topics.HotelAvailabilitySearchesProducer;
import com.cosmin.hotels.infrastructure.topics.dto.HotelAvailabilitySearchEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HotelAvailabilitySearchesController {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchesController.class);

    private HotelAvailabilitySearchesProducer hotelAvailabilitySearchesProducer;
    private ItemRepository itemRepository;

    @Autowired
    public HotelAvailabilitySearchesController(HotelAvailabilitySearchesProducer hotelAvailabilitySearchesProducer, ItemRepository itemRepository) {
        this.hotelAvailabilitySearchesProducer = hotelAvailabilitySearchesProducer;
        this.itemRepository = itemRepository;
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
    public ResponseEntity<CountResponseDto> get(@PathVariable(name = "searchId") String searchId) {
        HotelAvailabilitySearchDocument hotelAvailabilitySearchDocument = itemRepository.findItemBySearchId(searchId).orElseThrow();
        List<HotelAvailabilitySearchDocument> hotelAvailabilitySearchDocumentList =
                itemRepository.findAll(
                        hotelAvailabilitySearchDocument.getHotelId(),
                        hotelAvailabilitySearchDocument.getCheckInDate(),
                        hotelAvailabilitySearchDocument.getCheckOutDate(),
                        hotelAvailabilitySearchDocument.getAges());
        return ResponseEntity.ok(new CountResponseDto.Builder()
                .count((long) hotelAvailabilitySearchDocumentList.size())
                .search(new HotelAvailabilitySearchRequestDto.Builder().hotelId(hotelAvailabilitySearchDocument.getHotelId()).build())
                .build());

    }

}
