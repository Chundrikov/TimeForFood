package com.example.consumer.rest;

import com.example.consumer.dto.ConsumerDto;
import com.example.consumer.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;



@WebMvcTest(ConsumerController.class)
@AutoConfigureMockMvc
class ConsumerControllerTest {

    @MockBean
    private ConsumerService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllConsumersWithSuccess() throws Exception {
        ConsumerDto one = new ConsumerDto(1L, "Vasiya", "79865693214", "example@yandex.ru", "Test street");
        ConsumerDto two = new ConsumerDto(2L, "Vasiya", "79865693214", "example@yandex.ru", "Test street");

        List<ConsumerDto> consumerDtoList = new ArrayList<>();
        consumerDtoList.add(one);
        consumerDtoList.add(two);
        doReturn(consumerDtoList).when(service).getAllConsumers();

        mockMvc.perform(get("/api/consumers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Vasiya")))
                .andExpect(jsonPath("$[0].phone", is("79865693214")))
                .andExpect(jsonPath("$[0].email", is("example@yandex.ru")))
                .andExpect(jsonPath("$[0].address", is("Test street")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Vasiya")))
                .andExpect(jsonPath("$[1].phone", is("79865693214")))
                .andExpect(jsonPath("$[1].email", is("example@yandex.ru")))
                .andExpect(jsonPath("$[1].address", is("Test street")));
    }

    @Test
    public void getConsumerByIdWithSuccess() throws Exception {
        ConsumerDto one = new ConsumerDto(1L, "Vasiya", "79865693214", "example@yandex.ru", "Test street");
        doReturn(one).when(service).getConsumerById(1L);

        mockMvc.perform(get("/api/consumers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Vasiya")))
                .andExpect(jsonPath("$.phone", is("79865693214")))
                .andExpect(jsonPath("$.email", is("example@yandex.ru")))
                .andExpect(jsonPath("$.address", is("Test street")));

    }

    @Test
    public void createConsumer() throws Exception {
        ConsumerDto one = new ConsumerDto(1L, "Vasiya", "79865693214", "example@yandex.ru", "Test street");

        doReturn(one.getId()).when(service).create(anyString(), anyString(), anyString(), anyString());

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/api/consumers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(one.getId())));

    }

    @Test
    public void updateCustomer() throws Exception {
        ConsumerDto consumerCreated = new ConsumerDto(1L, "Vasiya", "79865693214", "example@yandex.ru", "Test street");
        ConsumerDto consumerUpdated = new ConsumerDto(1L, "Pavel", "79865693214", "example@yandex.ru", "Test street");

        doReturn(consumerCreated).when(service).getConsumerById(1L);
        doReturn(consumerUpdated.getId()).when(service).create(anyString(), anyString(), anyString(), anyString());
        doReturn(consumerUpdated).when(service).update(consumerCreated, 1L);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/api/consumers/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(consumerUpdated)));
    }



}