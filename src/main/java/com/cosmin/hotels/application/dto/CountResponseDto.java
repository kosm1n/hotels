package com.cosmin.hotels.application.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountResponseDto {

    @JsonProperty("searchId")
    private final String searchId;

    @JsonProperty("search")
    private final HotelAvailabilitySearchRequestDto search;

    @JsonProperty("count")
    private final Long count;

    private CountResponseDto(Builder builder) {
        searchId = builder.searchId;
        search = builder.search;
        count = builder.count;
    }

    public static Builder builder(CountResponseDto copy) {
        Builder builder = new Builder();
        builder.searchId = copy.getSearchId();
        builder.search = copy.getSearch();
        builder.count = copy.getCount();
        return builder;
    }

    public String getSearchId() {
        return searchId;
    }

    public HotelAvailabilitySearchRequestDto getSearch() {
        return search;
    }

    public Long getCount() {
        return count;
    }

    @JsonCreator
    public CountResponseDto(String searchId, HotelAvailabilitySearchRequestDto search, Long count) {
        this.searchId = searchId;
        this.search = search;
        this.count = count;
    }


    public static final class Builder {
        private String searchId;
        private HotelAvailabilitySearchRequestDto search;
        private Long count;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder searchId(String val) {
            searchId = val;
            return this;
        }

        public Builder search(HotelAvailabilitySearchRequestDto val) {
            search = val;
            return this;
        }

        public Builder count(Long val) {
            count = val;
            return this;
        }

        public CountResponseDto build() {
            return new CountResponseDto(this);
        }
    }
}
