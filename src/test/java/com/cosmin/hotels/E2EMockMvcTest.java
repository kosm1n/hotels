package com.cosmin.hotels;

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
public class E2EMockMvcTest {

    final static Logger LOG = LoggerFactory.getLogger(E2EMockMvcTest.class);

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
        HotelEvent hotelEvent =
                new HotelEvent.Builder()
                        .searchId("abcde")
                        .hotelId("abcdAbcd")
                        .checkIn(LocalDate.now())
                        .checkOut(LocalDate.now())
                        .ages(new Integer[]{1,2,3,4})
                        .build();
        ArgumentCaptor<Hotel> captor = ArgumentCaptor.forClass(Hotel.class);
        //User user = new User("11111", "John", "Wick");

        hotelProducer.send("hotel-availability-searches", hotelEvent);

        verify(hotelService, timeout(5000)).saveHotelAvailabilitySearchEvent(captor.capture());
        assertNotNull(captor.getValue());
        assertEquals("abcde", captor.getValue().getSearchId());
    }

//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    void givenAnHotelAvailabilitySearch_whenTryingToPost_thenReturnTheSearchId() throws Exception {
//
//        // Arrange
//
//        // Act
//        mvc.perform(MockMvcRequestBuilders.post("/search").contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(
//                "{\n" +
//                        "    \"hotelId\": \"1234aBc\",\n" +
//                        "    \"checkIn\": \"29/12/2023\",\n" +
//                        "    \"checkOut\": \"31/12/2023\",\n" +
//                        "    \"ages\": [\n" +
//                        "        30,\n" +
//                        "        29,\n" +
//                        "        1,\n" +
//                        "        3\n" +
//                        "    ]\n" +
//                        "}"))
//
//                // Assert
//                .andExpect(status().isOk());
//
//
//    }



}
