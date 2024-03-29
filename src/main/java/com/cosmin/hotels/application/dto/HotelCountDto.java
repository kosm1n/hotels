package com.cosmin.hotels.application.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelCountDto {

    @JsonProperty("searchId")
    private final String searchId;

    @JsonProperty("search")
    private final HotelDto search;

    @JsonProperty("count")
    private final Integer count;

    private HotelCountDto(Builder builder) {
        searchId = builder.searchId;
        search = builder.search;
        count = builder.count;
    }

    public static Builder builder(HotelCountDto copy) {
        Builder builder = new Builder();
        builder.searchId = copy.getSearchId();
        builder.search = copy.getSearch();
        builder.count = copy.getCount();
        return builder;
    }

    public String getSearchId() {
        return searchId;
    }

    public HotelDto getSearch() {
        return search;
    }

    public Integer getCount() {
        return count;
    }

    @JsonCreator
    public HotelCountDto(String searchId, HotelDto search, Integer count) {
        this.searchId = searchId;
        this.search = search;
        this.count = count;
    }


    public static final class Builder {
        private String searchId;
        private HotelDto search;
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

        public Builder search(HotelDto val) {
            search = val;
            return this;
        }

        public Builder count(Integer val) {
            count = val;
            return this;
        }

        public HotelCountDto build() {
            return new HotelCountDto(this);
        }
    }
}
