package com.cosmin.hotels.infrastructure.topics.producer;

import com.cosmin.hotels.infrastructure.topics.dto.HotelAvailabilitySearchEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HotelAvailabilitySearchesProducer {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchesProducer.class);

    @Autowired
    private KafkaTemplate<String, HotelAvailabilitySearchEvent> greetingTemplate;

    public void send(String topic, HotelAvailabilitySearchEvent payload) {
        LOG.info("Sending payload={} to topic={}", payload, topic);
        greetingTemplate.send(topic, payload);
    }

}
