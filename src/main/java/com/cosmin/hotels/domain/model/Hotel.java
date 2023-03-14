package com.cosmin.hotels.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class Hotel {

    private String searchId;
    private String hotelId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer[] ages;
    private Integer count;

    private Hotel(Builder builder) {
        searchId = builder.searchId;
        hotelId = builder.hotelId;
        checkIn = builder.checkIn;
        checkOut = builder.checkOut;
        ages = builder.ages;
        count = builder.count;
    }

    public static Builder builder(Hotel copy) {
        Builder builder = new Builder();
        builder.searchId = copy.getSearchId();
        builder.hotelId = copy.getHotelId();
        builder.checkIn = copy.getCheckIn();
        builder.checkOut = copy.getCheckOut();
        builder.ages = copy.getAges();
        builder.count = copy.getCount();
        return builder;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Integer[] getAges() {
        return ages;
    }

    public void setAges(Integer[] ages) {
        this.ages = ages;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonCreator
    public Hotel(String searchId, String hotelId, LocalDate checkIn, LocalDate checkOut, Integer[] ages, Integer count) {
        this.searchId = searchId;
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
        this.count = count;
    }


    public static final class Builder {
        private String searchId;
        private String hotelId;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private Integer[] ages;
        private Integer count;

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

        public Builder checkIn(LocalDate val) {
            checkIn = val;
            return this;
        }

        public Builder checkOut(LocalDate val) {
            checkOut = val;
            return this;
        }

        public Builder ages(Integer[] val) {
            ages = val;
            return this;
        }

        public Builder count(Integer val) {
            count = val;
            return this;
        }

        public Hotel build() {
            return new Hotel(this);
        }
    }
}
