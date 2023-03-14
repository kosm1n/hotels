package com.cosmin.hotels.application;

import com.cosmin.hotels.infrastructure.storage.documents.HotelDocument;
import com.cosmin.hotels.infrastructure.storage.repositories.HotelDocumentRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class HotelControllerTest {

    final static Logger LOG = LoggerFactory.getLogger(HotelControllerTest.class);

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
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private HotelDocumentRepository hotelDocumentRepository;

    @Test
    void givenAnHotelAvailabilitySearch_whenTryingToPost_thenReturnTheSearchId() throws Exception {

        // Arrange

        // Act
        mvc.perform(MockMvcRequestBuilders.post("/search").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(
        "{\n" +
                "    \"hotelId\": \"1234aBc\",\n" +
                "    \"checkIn\": \"29/12/2023\",\n" +
                "    \"checkOut\": \"31/12/2023\",\n" +
                "    \"ages\": [\n" +
                "        30,\n" +
                "        29,\n" +
                "        1,\n" +
                "        3\n" +
                "    ]\n" +
                "}"))

        // Assert
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.searchId").isString());


    }

    @Test
    void givenAnExistentSearchId_whenTryingToGet_thenReturnTheHotelDto() throws Exception {

        // Arrange
        hotelDocumentRepository.save(new HotelDocument.Builder()
                .searchId("abcde")
                .hotelId("abcdAbcd")
                .checkIn(LocalDate.now())
                .checkOut(LocalDate.now())
                .ages(new Integer[]{1,2,3,4})
                .build());

        // Act
        mvc.perform(MockMvcRequestBuilders.get("/count/abcde").contentType(MediaType.APPLICATION_JSON))

                // Assert
                .andExpect(status().isOk());


    }

    @Test
    void givenAnInvalidHotelAvailabilitySearch_whenTryingToPost_thenReturn400BadRequest() throws Exception {

        // Arrange

        // Act
        mvc.perform(MockMvcRequestBuilders.post("/search").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(
                                "{\n" +

                                        "    \"checkOut\": \"31/12/2023\",\n" +
                                        "    \"ages\": [\n" +
                                        "        30,\n" +
                                        "        29,\n" +
                                        "        1,\n" +
                                        "        3\n" +
                                        "    ]\n" +
                                        "}"))

                // Assert
                .andExpect(status().isBadRequest());


    }

}
