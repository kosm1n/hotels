package com.cosmin.hotels.application.dto;


import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelAvailabilitySearchRequestDto {

    @JsonProperty("hotelId")
    @NotBlank
    private final String hotelId;

    @JsonProperty("checkIn")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private final LocalDate checkIn;

    @JsonProperty("checkOut")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private final LocalDate checkOut;

    @JsonProperty("ages")
    @NotEmpty
    private final Integer[] ages;

    public String getHotelId() {
        return hotelId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public Integer[] getAges() {
        return ages;
    }

    @JsonCreator
    public HotelAvailabilitySearchRequestDto(
            @JsonProperty("hotelId") String hotelId,
            @JsonProperty("checkIn") LocalDate checkIn,
            @JsonProperty("checkOut") LocalDate checkOut,
            @JsonProperty("ages") @NotEmpty Integer[] ages) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }
}
