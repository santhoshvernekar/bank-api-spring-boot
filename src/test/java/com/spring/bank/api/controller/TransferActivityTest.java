package com.spring.bank.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.bank.api.error.BankApplicationException;
import com.spring.bank.api.service.ITransferActivityService;
import com.spring.bank.api.utils.TestDataHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransferActivityTest {

    private MockMvc resource;
    @Mock
    private ITransferActivityService transferActivityService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TransferActivityController transferActivityController = new TransferActivityController(transferActivityService);
        resource = MockMvcBuilders.standaloneSetup(transferActivityController)
                .build();
    }

    @Test
    public void should_Check_Transfer_Amount() throws Exception {
        String target = "/api/v1/transfer-activities/card";
        MvcResult result = resource.perform(post(target)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataHelper.MAPPER.writeValueAsString(TestDataHelper.getTransferActivityByCardDto())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();
        JsonNode jsonOutput = TestDataHelper.MAPPER.readTree(result.getResponse().getContentAsString());
        Assert.assertEquals("Transferred Successfully", jsonOutput.get("status").textValue());
    }

    // Need to add conditions
    @Test
    public void should_Check_Transfer_Amount_with_Zero_Amount() throws Exception {
        String target = "/api/v1/transfer-activities/card";
        resource.perform(post(target)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataHelper.MAPPER.writeValueAsString(TestDataHelper.getTransferActivityByCardDtoWithZeroAmount())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();
    }

    @Test
    public void should_Check_Transfer_Amount_with_Negative_Amount() throws Exception {
        Mockito.doThrow(BankApplicationException.to("Negative Amount"))
                .when(transferActivityService).transferByCardDetails(TestDataHelper.getTransferActivityByCardDtoWithNegativeAmount());
        String target = "/api/v1/transfer-activities/cards";
        resource.perform(post(target)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataHelper.MAPPER.writeValueAsString(TestDataHelper.getTransferActivityByCardDtoWithNegativeAmount())))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();
    }

    @Test
    public void should_Check_Transfer_Amount_By_Account() throws Exception {
        String target = "/api/v1/transfer-activities";
        resource.perform(post(target)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataHelper.MAPPER.writeValueAsString(TestDataHelper.getTransferActivityByAccountDto())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();
    }
}
