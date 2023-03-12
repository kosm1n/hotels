package com.cosmin.hotels.infrastructure.topics;

import com.cosmin.hotels.infrastructure.storage.HotelAvailabilitySearchDocument;
import com.cosmin.hotels.infrastructure.storage.ItemRepository;
import com.cosmin.hotels.infrastructure.topics.dto.HotelAvailabilitySearchEvent;
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

    @Value(value = "${com.kafka.topics.hotel-availability-searches-name}")
    private String topic;

    @Autowired
    private ItemRepository itemRepository;

    @KafkaListener(topics = "${com.kafka.topics.hotel-availability-searches-name}")
    public void send(@Payload HotelAvailabilitySearchEvent payload) {
        LOG.info("Received payload={} from topic={}", payload, topic);
        itemRepository.save(new HotelAvailabilitySearchDocument.Builder()
                .searchId(payload.getSearchId())
                .hotelId(payload.getHotelId())
                .checkIn(payload.getCheckIn())
                .checkOut(payload.getCheckOut())
                .ages(payload.getAges()).build());
    }
    
}