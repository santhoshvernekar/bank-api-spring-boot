package com.spring.bank.api.controller;

import com.spring.bank.api.service.ITransactionAuditService;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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
class TransactionAuditControllerTest {
    @Mock
    private ITransactionAuditService transactionAuditService;
    @Autowired
    private MockMvc resource;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TransactionAuditController transactionAuditController = new TransactionAuditController(transactionAuditService);
        resource = MockMvcBuilders.standaloneSetup(transactionAuditController)
                .build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/v1/transaction-audits", "/api/v1/transaction-audits/accounts/1", "/api/v1/transaction-audits/cards/1"})
    void should_get_Transaction_List(String target) throws Exception {
        resource.perform(get(target)).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();
    }


    @Test
    public void should_get_Transaction_List_ByCardNumber() throws Exception {
        String target = "/api/v1/transaction-audits/filters";
        resource.perform(get(target).param("cardNumber", "1")).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();
    }
}
