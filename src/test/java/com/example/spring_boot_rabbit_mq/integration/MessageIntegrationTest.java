package com.example.spring_boot_rabbit_mq.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRabbitMQIntegration() throws Exception {
        mockMvc.perform(post("/api/v1/message")
                        .content("Integration Test Message")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }
}
