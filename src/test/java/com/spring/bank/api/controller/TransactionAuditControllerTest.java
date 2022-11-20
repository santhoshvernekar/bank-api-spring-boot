package com.spring.bank.api.controller;

import com.spring.bank.api.service.ITransactionAuditService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionAuditControllerTest {
    @Mock
    ITransactionAuditService transactionAuditService;
    private MockMvc resource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        TransactionAuditController transactionAuditController = new TransactionAuditController(transactionAuditService);
        resource = MockMvcBuilders.standaloneSetup(transactionAuditController)
                .build();
    }

    @Test
    public void should_get_Transaction_List() throws Exception {
        String target = "/api/v1/transaction-audits";
        resource.perform(get(target)).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();
    }

    @Test
    public void should_get_Transaction_List_ByAccountId() throws Exception {
        String target = "/api/v1/transaction-audits/accounts/1";
        resource.perform(get(target)).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();
    }

    @Test
    public void should_get_Transaction_List_ByCardId() throws Exception {
        String target = "/api/v1/transaction-audits/cards/1";
        resource.perform(get(target)).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();
    }

    @Test
    public void should_get_Transaction_List_ByCardNumber() throws Exception {
        String target = "/api/v1/transaction-audits/filters";
        resource.perform(get(target).param("cardNumber", "1")).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();
    }
}
