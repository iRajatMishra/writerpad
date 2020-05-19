package com.xebia.writerpad.controller;

import com.xebia.writerpad.service.BasicWriterpadService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
class WriterpadControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BasicWriterpadService basicWriterpadService;

    @Test
    public void shouldSaveWithOKStatus() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post(
                "/post").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"title\": \"How to learn Spring Boot\",\n" +
                        "    \"description\": \"Ever wonder how?\",\n" +
                        "    \"body\": \"You have to believe\",\n" +
                        "    \"tags\": [\n" +
                        "        \"java\",\n" +
                        "        \"Spring Boot\",\n" +
                        "        \"tutorial\"\n" +
                        "    ]\n" +
                        "}");
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
    }

}