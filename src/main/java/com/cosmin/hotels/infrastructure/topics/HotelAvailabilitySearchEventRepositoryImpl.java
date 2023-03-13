package com.cosmin.hotels.infrastructure.topics;

import com.cosmin.hotels.domain.model.HotelAvailabilitySearch;
import com.cosmin.hotels.domain.repositories.HotelAvailabilitySearchEventRepository;
import com.cosmin.hotels.infrastructure.topics.converters.HotelAvailabilitySearchToHotelAvailabilitySearchEventConverter;
import com.cosmin.hotels.infrastructure.topics.producer.HotelAvailabilitySearchesProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelAvailabilitySearchEventRepositoryImpl implements HotelAvailabilitySearchEventRepository {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchEventRepositoryImpl.class);

    private HotelAvailabilitySearchToHotelAvailabilitySearchEventConverter converter;
    private HotelAvailabilitySearchesProducer producer;

    @Autowired
    public HotelAvailabilitySearchEventRepositoryImpl(
            HotelAvailabilitySearchToHotelAvailabilitySearchEventConverter converter,
            HotelAvailabilitySearchesProducer producer) {
        this.converter = converter;
        this.producer = producer;
    }

    @Override
    public void send(String topic, HotelAvailabilitySearch payload) {
        LOG.info("Send Payload - HotelAvailabilitySearch");
        producer.send(topic, converter.convert(payload));
    }

}
