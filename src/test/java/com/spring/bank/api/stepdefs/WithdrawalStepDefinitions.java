package com.spring.bank.api.stepdefs;

import com.spring.bank.api.error.OutOfBalanceException;
import com.spring.bank.api.model.dto.WithdrawalActivityByAccountDto;
import com.spring.bank.api.model.dto.WithdrawalActivityByCardDto;
import com.spring.bank.api.service.IWithdrawalActivityService;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.math.BigDecimal;

@Slf4j
@AutoConfigureMockMvc
public class WithdrawalStepDefinitions extends CucumberIntegrationTest {

    @Autowired
    private IWithdrawalActivityService withdrawalActivityService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @When("I withdraw ${int} from Account with AccountId ${int}")
    public void i_withdraw_$_from_account_with_id_$(int int1, int int2) {
        log.info("Withdrawal Initiated : ");
        try {
            withdrawalActivityService.withDrawableByAccount(WithdrawalActivityByAccountDto.builder().accountId((long) int2)
                    .amount(BigDecimal.valueOf(int1)).build());
        } catch (OutOfBalanceException e) {
            log.info("Withdrawal is not Possible, Reason: {}", e.getMessage());
        } catch (Exception e) {
            log.info("Withdrawal is not Possible,Reason : {}", e.getMessage());
        }
    }

    @When("I withdraw ${int} from Account with CardId ${int}")
    public void i_withdraw_$_from_account_with_card_id_$(int int1, int int2) {
        log.info("Withdrawal Process Initiated");
        try {
            withdrawalActivityService.withDrawableByCard(
                    WithdrawalActivityByCardDto.builder()
                            .amount(BigDecimal.valueOf(int1))
                            .cardId(Long.valueOf(int2)).build());
        } catch (OutOfBalanceException e) {
            log.info("Withdrawal is not Possible, Reason: {}", e.getMessage());
        } catch (Exception e) {
            log.info("Withdrawal is not Possible,Reason : {}", e.getMessage());
        }

    }
}
