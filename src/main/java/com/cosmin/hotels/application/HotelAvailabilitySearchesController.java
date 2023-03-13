package com.cosmin.hotels.application;


import com.cosmin.hotels.application.converters.HotelAvailabilitySearchDtoToHotelAvailabilitySearchConverter;
import com.cosmin.hotels.application.converters.HotelAvailabilitySearchToHotelAvailabilitySearchCountDtoConverter;
import com.cosmin.hotels.application.dto.HotelAvailabilitySearchCountDto;
import com.cosmin.hotels.application.dto.HotelAvailabilitySearchDto;
import com.cosmin.hotels.application.dto.HotelAvailabilitySearchIdDto;
import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;
import com.cosmin.hotels.domain.services.HotelAvailabilitySearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class HotelAvailabilitySearchesController {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchesController.class);

    private HotelAvailabilitySearchService hotelAvailabilitySearchService;
    private HotelAvailabilitySearchDtoToHotelAvailabilitySearchConverter toHotelAvailabilitySearchConverter;
    private HotelAvailabilitySearchToHotelAvailabilitySearchCountDtoConverter toHotelAvailabilitySearchCountDtoConverter;

    @Autowired
    public HotelAvailabilitySearchesController(
            HotelAvailabilitySearchService hotelAvailabilitySearchService,
            HotelAvailabilitySearchDtoToHotelAvailabilitySearchConverter toHotelAvailabilitySearchConverter,
            HotelAvailabilitySearchToHotelAvailabilitySearchCountDtoConverter toHotelAvailabilitySearchCountDtoConverter) {
        this.hotelAvailabilitySearchService = hotelAvailabilitySearchService;
        this.toHotelAvailabilitySearchConverter = toHotelAvailabilitySearchConverter;
        this.toHotelAvailabilitySearchCountDtoConverter = toHotelAvailabilitySearchCountDtoConverter;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<HotelAvailabilitySearchIdDto> hotelAvailabilitySearch(@Valid @RequestBody HotelAvailabilitySearchDto request) {
        LOG.info("HotelAvailabilitySearchesController - POST /search with RequestBody {}", request);

        HotelAvailabilitySearch hotelAvailabilitySearch =
                hotelAvailabilitySearchService.sendHotelAvailabilitySearch(
                        toHotelAvailabilitySearchConverter.convert(request));

        return ResponseEntity.ok(new HotelAvailabilitySearchIdDto.Builder().hotelId(hotelAvailabilitySearch.getSearchId()).build());
    }

    @GetMapping(value = "/count/{searchId}")
    public ResponseEntity<HotelAvailabilitySearchCountDto> count(@PathVariable(name = "searchId") String searchId) {
        LOG.info("HotelAvailabilitySearchesController - GET /count/{searchId} with {}", searchId);

        return ResponseEntity.ok(toHotelAvailabilitySearchCountDtoConverter.convert(
                hotelAvailabilitySearchService.countHotelAvailabilitySearch(searchId)));

    }

}
