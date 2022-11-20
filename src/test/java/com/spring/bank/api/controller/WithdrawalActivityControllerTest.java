package com.spring.bank.api.controller;

import com.spring.bank.api.service.IWithdrawalActivityService;
import com.spring.bank.api.utils.TestDataHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WithdrawalActivityControllerTest {
    @Mock
    IWithdrawalActivityService withdrawalActivityService;
    private MockMvc resource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        WithdrawalActivityController withdrawalActivityController = new WithdrawalActivityController(withdrawalActivityService);
        resource = MockMvcBuilders.standaloneSetup(withdrawalActivityController)
                .build();
    }

    @Test
    public void should_Check_Withdrawal_Amount() throws Exception {
        String target = "/api/v1/withdrawal-activities/card";
        resource.perform(post(target)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataHelper.MAPPER.writeValueAsString(TestDataHelper.getWithdrawalActivityByCardDto())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();
    }

    @Test
    public void should_Check_Withdrawal_Amount_ByAccountDetails() throws Exception {
        String target = "/api/v1/withdrawal-activities";
        resource.perform(post(target)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataHelper.MAPPER.writeValueAsString(TestDataHelper.getWithdrawalActivityByAccountDto())))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();
    }

}
