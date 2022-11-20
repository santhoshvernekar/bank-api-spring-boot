package com.spring.bank.api.stepdefs;


import com.spring.bank.api.error.OutOfBalanceException;
import com.spring.bank.api.model.dto.TransferActivityByAccountDto;
import com.spring.bank.api.model.dto.TransferActivityByCardDto;
import com.spring.bank.api.service.ITransferActivityService;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Slf4j
public class TransferFunctionStepDefinitions extends CucumberIntegrationTest {
    @Autowired
    private ITransferActivityService transferActivityService;

    @When("I transfer ${int} from First Account with AccountId ${int} to Account with AccountId ${int}")
    public void i_transfer_$_from_first_account_with_id_$_to_account_with_id_$(int int1, int int2, int int3) {
        log.info("Transfer Process Initiated ");
        try {
            transferActivityService.transferByAccountDetails(TransferActivityByAccountDto.builder().
                    amount(BigDecimal.valueOf(int1)).fromAccountId(Long.valueOf(int2)).
                    toAccountId(Long.valueOf(int3)).description("Transfer").build());
        } catch (OutOfBalanceException e) {
            log.info("Transfer is not Possible,Reason : {}", e.getMessage());
        } catch (Exception e) {
            log.info("Transfer is not Possible,Reason : {}", e.getMessage());
        }
    }

    @When("I transfer ${int} from First Account with CardId ${int} to Account with CardId ${int}")
    public void i_transfer_$_from_first_account_with_card_id_$_to_account_with_card_id_$(int int1, int int2, int int3) {
        log.info("Transfer Process Initiated ");
        try {
            transferActivityService.transferByCardDetails(TransferActivityByCardDto.builder()
                    .amount(BigDecimal.valueOf(int1)).fromCardId(Long.valueOf(int2))
                    .toAccountId(Long.valueOf(int3)).build());
        } catch (OutOfBalanceException e) {
            log.info("Transfer is not Possible,Reason : {}", e.getMessage());
        } catch (Exception e) {
            log.info("Transfer is not Possible,Reason : {}", e.getMessage());
        }
    }
}
