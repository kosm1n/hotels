package com.cosmin.hotels.infrastructure.topics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class HotelEvent {

    @JsonProperty("searchId")
    private String searchId;
    @JsonProperty("hotelId")
    private String hotelId;

    @JsonProperty("checkIn")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate checkIn;

    @JsonProperty("checkOut")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate checkOut;

    @JsonProperty("ages")
    private Integer[] ages;

    public HotelEvent(String searchId, String hotelId, LocalDate checkIn, LocalDate checkOut, Integer[] ages) {
        this.searchId = searchId;
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }

    public HotelEvent() {}

    public String getSearchId() {
        return searchId;
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

    private HotelEvent(Builder builder) {
        searchId = builder.searchId;
        hotelId = builder.hotelId;
        checkIn = builder.checkIn;
        checkOut = builder.checkOut;
        ages = builder.ages;
    }

    public static Builder builder(HotelEvent copy) {
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
        private LocalDate checkIn;
        private LocalDate checkOut;
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

        public HotelEvent build() {
            return new HotelEvent(this);
        }
    }
}
