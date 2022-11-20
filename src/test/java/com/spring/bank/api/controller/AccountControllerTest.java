package com.spring.bank.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.bank.api.service.IAccountService;
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
import org.springframework.web.util.NestedServletException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Mock
    private IAccountService accountService;
    private MockMvc resource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AccountController accountController = new AccountController(accountService);
        resource = MockMvcBuilders.standaloneSetup(accountController).build();
        when(accountService.getAccountById(5L)).thenReturn(TestDataHelper.getAccountInfoWithCard(5L));
        when(accountService.getBankAccountList()).thenReturn(TestDataHelper.getAccountList(5L));
    }

    @Test
    public void should_get_Account_List() throws Exception {
        String target = "/api/v1/accounts";
        resource.perform(get(target)).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();
    }

    @Test
    public void should_get_Account_ById() throws Exception {
        String target = "/api/v1/accounts/5";
        MvcResult result = resource.perform(get(target)).andDo(print()).
                andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();

        JsonNode jsonOutput = TestDataHelper.MAPPER.readTree(result.getResponse().getContentAsString());
        Assert.assertEquals("1000", jsonOutput.get("currentBalance").asText());
    }

    @Test
    public void should_be_Able_Insert_Account() throws Exception {
        String target = "/api/v1/accounts";
        resource.perform(put(target)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataHelper.MAPPER.writeValueAsString(TestDataHelper.getAccountDtoWithCard())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();
    }

    @Test(expected = NestedServletException.class)
    public void should_be_Able_Insert_Account_Without_Card() throws Exception {
        String target = "/api/v1/accounts";
        resource.perform(put(target)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataHelper.MAPPER.writeValueAsString(TestDataHelper.getAccountDtoWithOutCard())))
                .andExpect(status().is5xxServerError())
                .andDo(print()).andReturn();
    }

    @Test
    public void should_get_Account_Balances_ById() throws Exception {
        String target = "/api/v1/accounts/balances/5";
        resource.perform(get(target)).andDo(print()).
                andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();

    }
}
