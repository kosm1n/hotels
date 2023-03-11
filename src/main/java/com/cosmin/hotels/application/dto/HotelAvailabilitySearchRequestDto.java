package com.cosmin.hotels.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class HotelAvailabilitySearchRequestDto {

    @JsonProperty("hotelId")
    @NotBlank
    private final String hotelId;

    @JsonProperty("checkIn")
    private final Date checkIn;

    @JsonProperty("checkOut")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private final Date checkOut;

    @JsonProperty("ages")
    @NotEmpty
    private final Integer[] ages;

    public HotelAvailabilitySearchRequestDto(String hotelId, Date checkIn, Date checkOut, Integer[] ages) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }

    private HotelAvailabilitySearchRequestDto(Builder builder) {
        hotelId = builder.hotelId;
        checkIn = builder.checkIn;
        checkOut = builder.checkOut;
        ages = builder.ages;
    }

    public static Builder builder(HotelAvailabilitySearchRequestDto copy) {
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

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public Integer[] getAges() {
        return ages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelAvailabilitySearchRequestDto that = (HotelAvailabilitySearchRequestDto) o;
        return Objects.equals(hotelId, that.hotelId) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut) && Arrays.equals(ages, that.ages);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(hotelId, checkIn, checkOut);
        result = 31 * result + Arrays.hashCode(ages);
        return result;
    }

    public static final class Builder {
        private String hotelId;
        private Date checkIn;
        private Date checkOut;
        private Integer[] ages;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder hotelId(String val) {
            hotelId = val;
            return this;
        }

        public Builder checkIn(Date val) {
            checkIn = val;
            return this;
        }

        public Builder checkOut(Date val) {
            checkOut = val;
            return this;
        }

        public Builder ages(Integer[] val) {
            ages = val;
            return this;
        }

        public HotelAvailabilitySearchRequestDto build() {
            return new HotelAvailabilitySearchRequestDto(this);
        }
    }
}
