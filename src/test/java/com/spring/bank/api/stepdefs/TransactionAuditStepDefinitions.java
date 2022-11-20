package com.spring.bank.api.stepdefs;
/**
 * @author Santhosh
 */

import com.spring.bank.api.model.entity.TransactionAudit;
import com.spring.bank.api.service.ITransactionAuditService;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class TransactionAuditStepDefinitions extends CucumberIntegrationTest {
    @Autowired
    private ITransactionAuditService transactionAuditService;

    @Then("Transactions should be in Audits for Account with AccountId ${int}")
    public void transactions_should_be_in_audits(int int1) {
        List<TransactionAudit> list = transactionAuditService.getAuditByAccountId((long) int1);
        logTransactionAudit(list);
    }

    @Then("Transactions should be in Audits for Account with CardId ${int}")
    public void transactions_should_be_in_audits_for_account_with_card_id_$(int int1) {
        List<TransactionAudit> list = transactionAuditService.getAuditByCardId((long) int1);
        logTransactionAudit(list);
    }

    private void logTransactionAudit(List<TransactionAudit> list) {
        log.info("The Number of Audit Transactions Captured  is : {}", list.size());
        log.info("{}:", list.toString());
        log.info("All Audit Info: {}", transactionAuditService.getAllAudits());
    }

}
