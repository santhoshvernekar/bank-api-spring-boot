package com.spring.bank.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.bank.api.service.ICustomerService;
import com.spring.bank.api.utils.TestDataHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Mock
    private ICustomerService customerService;

    private MockMvc resource;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustomerController customerController = new CustomerController(customerService);
        resource = MockMvcBuilders.standaloneSetup(customerController).build();
        when(customerService.getCustomerList()).thenReturn(TestDataHelper.getCustomerList(5L));
        when(customerService.getCustomer(any())).thenReturn(TestDataHelper.getCustomerObject(5L));
    }

    @Test
    public void should_get_Customer_List() throws Exception {
        String target = "/api/v1/customers";
        MvcResult result = resource.perform(get(target)).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();
        JsonNode jsonOutput = TestDataHelper.MAPPER.readTree(result.getResponse().getContentAsString());
        Assert.assertEquals("Santhosh", jsonOutput.get("items").get(0).get("firstName").textValue());
    }

    @Test
    public void should_get_Customer_ById() throws Exception {
        String target = "/api/v1/customers/1";
        MvcResult result = resource.perform(get(target)).andDo(print()).
                andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();
        JsonNode jsonOutput = TestDataHelper.MAPPER.readTree(result.getResponse().getContentAsString());
        Assert.assertEquals("Santhosh", jsonOutput.get("firstName").textValue());
    }

    @Test
    public void should_Insert_Customer() throws Exception {
        String target = "/api/v1/customers";
        resource.perform(put(target)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataHelper.MAPPER.writeValueAsString(TestDataHelper.getCustomerDto())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();
    }
}
