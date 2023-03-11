package com.cosmin.hotels.domain.model;

public class HotelAvailabilitySearch {

    private final String hotelId;
    private final String checkIn;
    private final String checkOut;
    private final Integer[] ages;

    public String getHotelId() {
        return hotelId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public Integer[] getAges() {
        return ages;
    }

    private HotelAvailabilitySearch(HotelAvailabilitySearchDtoBuilder builder) {
        this.hotelId = builder.hotelId;
        this.checkIn = builder.checkIn;
        this.checkOut = builder.checkOut;
        this.ages = builder.ages;
    }


    public static final class HotelAvailabilitySearchDtoBuilder {
        private String hotelId;
        private String checkIn;
        private String checkOut;
        private Integer[] ages;

        public HotelAvailabilitySearchDtoBuilder(String hotelId, String checkIn, String checkOut, Integer[] ages) {
            this.hotelId = hotelId;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.ages = ages;
        }

        public HotelAvailabilitySearchDtoBuilder withHotelId(String hotelId) {
            this.hotelId = hotelId;
            return this;
        }

        public HotelAvailabilitySearchDtoBuilder withCheckIn(String checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        public HotelAvailabilitySearchDtoBuilder withCheckOut(String checkOut) {
            this.checkOut = checkOut;
            return this;
        }

        public HotelAvailabilitySearchDtoBuilder withAges(Integer[] ages) {
            this.ages = ages;
            return this;
        }

        public HotelAvailabilitySearch build() {
            return new HotelAvailabilitySearch(this);
        }
    }
}
