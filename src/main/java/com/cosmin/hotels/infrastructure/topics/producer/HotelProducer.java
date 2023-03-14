package com.cosmin.hotels.infrastructure.topics.producer;

import com.cosmin.hotels.infrastructure.topics.dto.HotelEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HotelProducer {

    final Logger LOG = LoggerFactory.getLogger(HotelProducer.class);

    private KafkaTemplate<String, HotelEvent> hotelsTemplate;

    @Autowired
    public HotelProducer(KafkaTemplate<String, HotelEvent> hotelsTemplate) {
        this.hotelsTemplate = hotelsTemplate;
    }

    public void send(String topic, HotelEvent payload) {
        LOG.info("Sending payload={} to topic={}", payload, topic);
        hotelsTemplate.send(topic, payload);
    }

}
