package com.mrhw.distancecalulator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrhw.distancecalulator.model.Distance;
import com.mrhw.distancecalulator.model.Request;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static com.mrhw.distancecalulator.model.LengthUnit.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class DistanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper MAPPER;

    private Distance Distance_10_ft = Distance.of(BigDecimal.TEN, FT);
    private Distance Distance_1_M = Distance.of(BigDecimal.valueOf(1), M);
    private Distance Distance_2_M = Distance.of(BigDecimal.valueOf(2), M);
    private Distance Distance_0_M = Distance.of(BigDecimal.ZERO, M);

    @SneakyThrows
    @Test
    public void test_should_add_distances() {
        Request request = new Request();
        request.setFirstDistance(Distance_1_M);
        request.setSecondDistance(Distance_2_M);
        request.setResultUnit(M);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/add")
                        .content(MAPPER.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Distance resulted = MAPPER.readValue(result.getResponse().getContentAsString(), Distance.class);
        assertEquals(resulted.getAmount(), BigDecimal.valueOf(3).setScale(2));
    }

    @SneakyThrows
    @Test
    public void test_should_convert_and_add_distances() {
        Request request = new Request();
        request.setFirstDistance(Distance_1_M);
        request.setSecondDistance(Distance_10_ft);
        request.setResultUnit(FT);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/add")
                        .content(MAPPER.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Distance resulted = MAPPER.readValue(result.getResponse().getContentAsString(), Distance.class);
        assertEquals(resulted.getAmount(), BigDecimal.valueOf(13.28).setScale(2));
        assertEquals(resulted.getLengthUnit(), FT);
    }

    @SneakyThrows
    @Test
    public void test_should_remove_distances() {
        Request request = new Request();
        request.setFirstDistance(Distance_1_M);
        request.setSecondDistance(Distance_2_M);
        request.setResultUnit(M);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/subtract")
                        .content(MAPPER.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Distance resulted = MAPPER.readValue(result.getResponse().getContentAsString(), Distance.class);
        assertEquals(resulted.getAmount(), BigDecimal.valueOf(-1).setScale(2));
    }

    @SneakyThrows
    @Test
    public void test_should_convert_and_remove_distances() {
        Request request = new Request();
        request.setFirstDistance(Distance_1_M);
        request.setSecondDistance(Distance_10_ft);
        request.setResultUnit(FT);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/subtract")
                        .content(MAPPER.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Distance resulted = MAPPER.readValue(result.getResponse().getContentAsString(), Distance.class);
        assertEquals(resulted.getAmount(), BigDecimal.valueOf(-6.72).setScale(2));
        assertEquals(resulted.getLengthUnit(), FT);
    }

    @SneakyThrows
    @Test
    public void test_should_multiply_distances() {
        Request request = new Request();
        request.setFirstDistance(Distance_1_M);
        request.setSecondDistance(Distance_2_M);
        request.setResultUnit(M);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/multiply")
                        .content(MAPPER.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Distance resulted = MAPPER.readValue(result.getResponse().getContentAsString(), Distance.class);
        assertEquals(resulted.getAmount(), BigDecimal.valueOf(2).setScale(2));
        assertEquals(resulted.getLengthUnit(), SQM);
    }

    @SneakyThrows
    @Test
    public void test_should_convert_and_multiply_distances() {
        Request request = new Request();
        request.setFirstDistance(Distance_1_M);
        request.setSecondDistance(Distance_10_ft);
        request.setResultUnit(FT);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/multiply")
                        .content(MAPPER.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Distance resulted = MAPPER.readValue(result.getResponse().getContentAsString(), Distance.class);
        assertEquals(resulted.getAmount(), BigDecimal.valueOf(32.80).setScale(2));
        assertEquals(resulted.getLengthUnit(), SQFT);
    }

    @SneakyThrows
    @Test
    public void test_should_divide_distances() {
        Request request = new Request();
        request.setFirstDistance(Distance_1_M);
        request.setSecondDistance(Distance_2_M);
        request.setResultUnit(M);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/divide")
                        .content(MAPPER.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resulted = result.getResponse().getContentAsString();
        assertEquals(resulted, BigDecimal.valueOf(0.5).setScale(2).toString());
    }

    @SneakyThrows
    @Test
    public void test_should_convert_and_divide_distances() {
        Request request = new Request();
        request.setFirstDistance(Distance_1_M);
        request.setSecondDistance(Distance_10_ft);
        request.setResultUnit(FT);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/divide")
                        .content(MAPPER.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resulted = result.getResponse().getContentAsString();
        assertEquals(resulted, BigDecimal.valueOf(0.33).setScale(2).toString());
    }

    @SneakyThrows
    @Test
    public void test_should_return_exception_divide_by_zero() {
        Request request = new Request();
        request.setFirstDistance(Distance_1_M);
        request.setSecondDistance(Distance_0_M);
        request.setResultUnit(FT);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/divide")
                        .content(MAPPER.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        String resulted = result.getResponse().getContentAsString();
        assertEquals(resulted, "Division by zero");
    }


}