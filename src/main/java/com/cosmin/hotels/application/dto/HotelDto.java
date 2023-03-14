package com.cosmin.hotels.application.dto;


import com.fasterxml.jackson.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Valid
public class HotelDto {

    @JsonProperty("hotelId")
    @NotEmpty
    private final String hotelId;

    @JsonProperty("checkIn")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private final LocalDate checkIn;

    @JsonProperty("checkOut")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private final LocalDate checkOut;

    @JsonProperty("ages")
    @NotEmpty
    private final Integer[] ages;

    private HotelDto(Builder builder) {
        hotelId = builder.hotelId;
        checkIn = builder.checkIn;
        checkOut = builder.checkOut;
        ages = builder.ages;
    }

    public static Builder builder(HotelDto copy) {
        Builder builder = new Builder();
        builder.hotelId = copy.getHotelId();
        builder.checkIn = copy.getCheckIn();
        builder.checkOut = copy.getCheckOut();
        builder.ages = copy.getAges();
        return builder;
    }

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
    @Valid
    public HotelDto(
            @NotEmpty @JsonProperty("hotelId") String hotelId,
            @JsonProperty("checkIn") LocalDate checkIn,
            @JsonProperty("checkOut") LocalDate checkOut,
            @JsonProperty("ages") @NotEmpty Integer[] ages) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }


    public static final class Builder {
        private @NotBlank String hotelId;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private @NotEmpty Integer[] ages;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder hotelId(@NotBlank String val) {
            hotelId = val;
            return this;
        }

        public Builder checkIn(LocalDate val) {
            checkIn = val;
            return this;
        }

        public Builder checkOut(LocalDate val) {
            checkOut = val;
            return this;
        }

        public Builder ages(@NotEmpty Integer[] val) {
            ages = val;
            return this;
        }

        public HotelDto build() {
            return new HotelDto(this);
        }
    }
}
