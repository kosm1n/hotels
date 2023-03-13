package com.cosmin.hotels.infrastructure.topics.consumer;

import com.cosmin.hotels.domain.services.HotelAvailabilitySearchService;
import com.cosmin.hotels.infrastructure.topics.converters.HotelAvailabilitySearchEventToHotelAvailabilitySearchConverter;
import com.cosmin.hotels.infrastructure.topics.dto.HotelAvailabilitySearchEvent;
import com.cosmin.hotels.infrastructure.topics.producer.HotelAvailabilitySearchesProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class HotelAvailabilitySearchesConsumer {

    final Logger LOG = LoggerFactory.getLogger(HotelAvailabilitySearchesProducer.class);

    private String topic;
    private HotelAvailabilitySearchService hotelAvailabilitySearchService;
    private HotelAvailabilitySearchEventToHotelAvailabilitySearchConverter toHotelAvailabilitySearchConverter;

    @Autowired
    public HotelAvailabilitySearchesConsumer(
            @Value(value = "${com.kafka.topics.hotel-availability-searches-name}") String topic,
            HotelAvailabilitySearchService hotelAvailabilitySearchService,
            HotelAvailabilitySearchEventToHotelAvailabilitySearchConverter toHotelAvailabilitySearchConverter) {
        this.topic = topic;
        this.hotelAvailabilitySearchService = hotelAvailabilitySearchService;
        this.toHotelAvailabilitySearchConverter = toHotelAvailabilitySearchConverter;
    }

    @KafkaListener(topics = "${com.kafka.topics.hotel-availability-searches-name}")
    public void send(@Payload HotelAvailabilitySearchEvent payload) {
        LOG.info("Received payload={} from topic={}", payload, topic);
        hotelAvailabilitySearchService.saveHotelAvailabilitySearchEvent(
                toHotelAvailabilitySearchConverter.convert(payload));
    }
    
}
