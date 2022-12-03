package com.spring.bank.api.stepdefs;

import com.spring.bank.api.model.entity.Card;
import com.spring.bank.api.service.ICardService;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CardStepDefinitions extends CucumberIntegrationTest {
    @Autowired
    private ICardService cardService;

    @Given("Card with CardId ${int} exist in System")
    public void card_with_id_$_exist_in_system(int int1) {
        Card card = cardService.getCardById((long) int1);
        log.info("Card TYPE is : {}", card.getCardType().toString());
        log.info("Card Details Are : {}", card.toString());
    }

    @Given("Card with CardNumber ${int} exist in System")
    public void card_with_number_$_exist_in_system(int int1) {
        Card card = cardService.getByCardNumber((long) int1);
        log.info("Card TYPE is : {}", card.getCardType().toString());
        log.info("Card Details Are : {}", card.toString());
    }
}

