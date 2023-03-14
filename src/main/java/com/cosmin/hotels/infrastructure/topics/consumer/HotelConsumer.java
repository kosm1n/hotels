package com.cosmin.hotels.infrastructure.topics.consumer;

import com.cosmin.hotels.domain.services.HotelService;
import com.cosmin.hotels.infrastructure.topics.converters.HotelEventToHotelConverter;
import com.cosmin.hotels.infrastructure.topics.dto.HotelEvent;
import com.cosmin.hotels.infrastructure.topics.producer.HotelProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class HotelConsumer {

    final Logger LOG = LoggerFactory.getLogger(HotelProducer.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private HotelEvent messageConsumed;
    private String topic;
    private HotelService hotelService;
    private HotelEventToHotelConverter toHotelAvailabilitySearchConverter;

    @Autowired
    public HotelConsumer(
            @Value(value = "${com.kafka.topics.hotel-availability-searches-name}") String topic,
            HotelService hotelService,
            HotelEventToHotelConverter toHotelAvailabilitySearchConverter) {
        this.topic = topic;
        this.hotelService = hotelService;
        this.toHotelAvailabilitySearchConverter = toHotelAvailabilitySearchConverter;
    }

    @KafkaListener(topics = "${com.kafka.topics.hotel-availability-searches-name}")
    public void send(@Payload HotelEvent payload) {
        LOG.info("Received payload={} from topic={}", payload, topic);
        messageConsumed = payload;
        hotelService.saveHotelAvailabilitySearchEvent(
                toHotelAvailabilitySearchConverter.convert(payload));
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public HotelEvent getHotelAvailabilitySearchEvent() {
        return messageConsumed;
    }

}
