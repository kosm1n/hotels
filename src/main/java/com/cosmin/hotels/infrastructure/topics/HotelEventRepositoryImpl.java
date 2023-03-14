package com.cosmin.hotels.infrastructure.topics;

import com.cosmin.hotels.domain.model.Hotel;
import com.cosmin.hotels.domain.repositories.HotelEventRepository;
import com.cosmin.hotels.infrastructure.topics.converters.HotelToHotelEventConverter;
import com.cosmin.hotels.infrastructure.topics.producer.HotelProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelEventRepositoryImpl implements HotelEventRepository {

    final Logger LOG = LoggerFactory.getLogger(HotelEventRepositoryImpl.class);

    private HotelToHotelEventConverter converter;
    private HotelProducer producer;

    @Autowired
    public HotelEventRepositoryImpl(
            HotelToHotelEventConverter converter,
            HotelProducer producer) {
        this.converter = converter;
        this.producer = producer;
    }

    @Override
    public void send(String topic, Hotel payload) {
        LOG.info("Send Payload - HotelAvailabilitySearch");
        producer.send(topic, converter.convert(payload));
    }

}
