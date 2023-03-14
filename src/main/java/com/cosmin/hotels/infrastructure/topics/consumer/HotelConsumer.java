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

@Component
public class HotelConsumer {

    final Logger LOG = LoggerFactory.getLogger(HotelProducer.class);

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
    public void consume(@Payload HotelEvent payload) {
        LOG.info("Received payload={} from topic={}", payload, topic);
        hotelService.saveHotelAvailabilitySearchEvent(
                toHotelAvailabilitySearchConverter.convert(payload));
    }


}
