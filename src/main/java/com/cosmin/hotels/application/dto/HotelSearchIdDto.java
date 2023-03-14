package com.cosmin.hotels.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelSearchIdDto {

    @JsonProperty("searchId")
    private final String searchId;

    @JsonCreator
    public HotelSearchIdDto(@JsonProperty("searchId") String searchId) {
        this.searchId = searchId;
    }

    private HotelSearchIdDto(Builder builder) {
        searchId = builder.searchId;
    }

    public static Builder builder(HotelSearchIdDto copy) {
        Builder builder = new Builder();
        builder.searchId = copy.getSearchId();
        return builder;
    }

    public String getSearchId() {
        return searchId;
    }


    public static final class Builder {
        private String searchId;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder searchId(String val) {
            searchId = val;
            return this;
        }

        public HotelSearchIdDto build() {
            return new HotelSearchIdDto(this);
        }
    }
}
