package com.cosmin.hotels.infrastructure.topics.dto;

import java.util.Date;

public class HotelAvailabilitySearchEvent {


    private final String searchId;
    private final String hotelId;
    private final Date checkIn;
    private final Date checkOut;
    private final Integer[] ages;

    public HotelAvailabilitySearchEvent(String searchId, String hotelId, Date checkIn, Date checkOut, Integer[] ages) {
        this.searchId = searchId;
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }

    public String getSearchId() {
        return searchId;
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

    private HotelAvailabilitySearchEvent(Builder builder) {
        searchId = builder.searchId;
        hotelId = builder.hotelId;
        checkIn = builder.checkIn;
        checkOut = builder.checkOut;
        ages = builder.ages;
    }

    public static Builder builder(HotelAvailabilitySearchEvent copy) {
        Builder builder = new Builder();
        builder.searchId = copy.getSearchId();
        builder.hotelId = copy.getHotelId();
        builder.checkIn = copy.getCheckIn();
        builder.checkOut = copy.getCheckOut();
        builder.ages = copy.getAges();
        return builder;
    }


    public static final class Builder {
        private String searchId;
        private String hotelId;
        private Date checkIn;
        private Date checkOut;
        private Integer[] ages;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder searchId(String val) {
            searchId = val;
            return this;
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

        public HotelAvailabilitySearchEvent build() {
            return new HotelAvailabilitySearchEvent(this);
        }
    }
}
