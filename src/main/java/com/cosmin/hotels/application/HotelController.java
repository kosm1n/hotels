package com.cosmin.hotels.application;


import com.cosmin.hotels.application.converters.HotelDtoToHotelConverter;
import com.cosmin.hotels.application.converters.HotelToHotelCountDtoConverter;
import com.cosmin.hotels.application.dto.HotelCountDto;
import com.cosmin.hotels.application.dto.HotelDto;
import com.cosmin.hotels.application.dto.HotelSearchIdDto;
import com.cosmin.hotels.domain.model.Hotel;
import com.cosmin.hotels.domain.services.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {

    final Logger LOG = LoggerFactory.getLogger(HotelController.class);

    private HotelService hotelService;
    private HotelDtoToHotelConverter toHotelAvailabilitySearchConverter;
    private HotelToHotelCountDtoConverter toHotelAvailabilitySearchCountDtoConverter;

    @Autowired
    public HotelController(
            HotelService hotelService,
            HotelDtoToHotelConverter toHotelAvailabilitySearchConverter,
            HotelToHotelCountDtoConverter toHotelAvailabilitySearchCountDtoConverter) {
        this.hotelService = hotelService;
        this.toHotelAvailabilitySearchConverter = toHotelAvailabilitySearchConverter;
        this.toHotelAvailabilitySearchCountDtoConverter = toHotelAvailabilitySearchCountDtoConverter;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<HotelSearchIdDto> search(@Validated @RequestBody final HotelDto request) {
        LOG.info("HotelAvailabilitySearchesController - POST /search with RequestBody {}", request);

        Hotel hotel =
                hotelService.sendHotelAvailabilitySearch(
                        toHotelAvailabilitySearchConverter.convert(request));

        return ResponseEntity.ok(new HotelSearchIdDto.Builder().searchId(hotel.getSearchId()).build());
    }

    @GetMapping(value = "/count/{searchId}")
    public ResponseEntity<HotelCountDto> count(@PathVariable(name = "searchId") final String searchId) {
        LOG.info("HotelAvailabilitySearchesController - GET /count/{searchId} with {}", searchId);

        return ResponseEntity.ok(toHotelAvailabilitySearchCountDtoConverter.convert(
                hotelService.countHotelAvailabilitySearch(searchId)));

    }

}
