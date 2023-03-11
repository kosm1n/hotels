package com.cosmin.hotels.application.dto;

import java.util.Objects;

public class HotelAvailabilitySearchResponseDto {

    private final String hotelId;

    private HotelAvailabilitySearchResponseDto(Builder builder) {
        hotelId = builder.hotelId;
    }

    public static Builder builder(HotelAvailabilitySearchResponseDto copy) {
        Builder builder = new Builder();
        builder.hotelId = copy.getHotelId();
        return builder;
    }

    public String getHotelId() {
        return hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelAvailabilitySearchResponseDto that = (HotelAvailabilitySearchResponseDto) o;
        return Objects.equals(hotelId, that.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId);
    }


    public static final class Builder {
        private String hotelId;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder hotelId(String val) {
            hotelId = val;
            return this;
        }

        public HotelAvailabilitySearchResponseDto build() {
            return new HotelAvailabilitySearchResponseDto(this);
        }
    }
}
