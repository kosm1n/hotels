package com.cosmin.hotels.domain;

import com.cosmin.hotels.domain.model.Hotel;
import com.cosmin.hotels.domain.services.HotelService;
import com.cosmin.hotels.infrastructure.topics.dto.HotelEvent;
import com.cosmin.hotels.infrastructure.topics.producer.HotelProducer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Testcontainers
public class HotelServiceTest {

    final static Logger LOG = LoggerFactory.getLogger(HotelServiceTest.class);

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Container
    static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        LOG.info(kafkaContainer.getBootstrapServers());
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Autowired
    private HotelProducer hotelProducer;

    @MockBean
    private HotelService hotelService;

    @Test
    void testProduceAndConsumeKafkaMessage() {

        // arrange
        HotelEvent hotelEvent =
                new HotelEvent.Builder()
                        .searchId("abcde")
                        .hotelId("abcdAbcd")
                        .checkIn(LocalDate.now())
                        .checkOut(LocalDate.now())
                        .ages(new Integer[]{1,2,3,4})
                        .build();
        ArgumentCaptor<Hotel> captor = ArgumentCaptor.forClass(Hotel.class);

        // act
        hotelProducer.send("hotel-availability-searches", hotelEvent);

        // assert
        verify(hotelService, timeout(5000)).saveHotelAvailabilitySearchEvent(captor.capture());
        assertNotNull(captor.getValue());
        assertEquals("abcde", captor.getValue().getSearchId());
    }

}
