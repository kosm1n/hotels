package com.cosmin.hotels.infrastructure.storage.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document("searches")
public class HotelDocument {

    @Id
    private String searchId;

    private String hotelId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate checkInDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate checkOutDate;

    private Integer[] ages;

    public HotelDocument(String searchId, String hotelId, LocalDate checkInDate, LocalDate checkOutDate, Integer[] ages) {
        this.searchId = searchId;
        this.hotelId = hotelId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.ages = ages;
    }

    public HotelDocument() {}

    public String getSearchId() {
        return searchId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public Integer[] getAges() {
        return ages;
    }

    private HotelDocument(Builder builder) {
        searchId = builder.searchId;
        hotelId = builder.hotelId;
        checkInDate = builder.checkIn;
        checkOutDate = builder.checkOut;
        ages = builder.ages;
    }

    public static Builder builder(HotelDocument copy) {
        Builder builder = new Builder();
        builder.searchId = copy.getSearchId();
        builder.hotelId = copy.getHotelId();
        builder.checkIn = copy.getCheckInDate();
        builder.checkOut = copy.getCheckOutDate();
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

        public HotelDocument build() {
            return new HotelDocument(this);
        }
    }
}
