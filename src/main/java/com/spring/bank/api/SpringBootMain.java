package com.spring.bank.api;

import com.spring.bank.api.model.entity.Customer;
import com.spring.bank.api.repository.CustomerRepository;
import com.spring.bank.api.utils.UtilityHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;


@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
public class SpringBootMain {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
    }

    /*
     * - Inserting Some Entries on Application load - Helps in Testing Code
     *
     *  * Assumptions Made : *
     *  - One Customer can have at max one Account
     *  - An Account is associated with either DEBIT Card or CREDIT Card
     *  - One Customer will have only one CARD
     *  - Account will always be associated with card
     *  - Fix Fee (1% for Credit card and 0 for others)
     *
     *  * Requirements :
     *  - It should be possible to transfer and withdraw money from an account. It is possible to pay with either debit card or credit card. If a transfer/withdraw is done with a credit card, 1% of the amount is charged extra. Use design patterns where applicable and write some test cases as well.
     *    REST Controllers are added
     *
     *  - A negative balance is not possible
     *    Exception is thrown
     *
     *  - One rest endpoint to see current available balance in all accounts
     *    AccountController
     *
     *  - One rest endpoint to withdraw money
     *    WithdrawalActivityController
     *
     *  - One rest endpoint to transfer money
     *    TransferActivity
     *
     *  - One credit card or debit card is linked with one account
     *    Account and Card has OneToOne mapping
     *
     *  - It should be able to audit transfers or withdrawals
     *    Audit is maintained
     *
     *  * Scope of Changes: *
     *  - Using value types wherever possible with Data Validations
     *  - Write More tests to cover All possible cases
     *  - Design decisions based on Potential future use cases
     * */
    @Bean
    CommandLineRunner runner() {
        return args -> {

            List<Customer> customers = customerRepository.findAll();

            if (customers.isEmpty()) {
                log.info("******* Inserting customers to DB *******");
                customerRepository.saveAll(UtilityHelper.customerSupplier.get());
            } else {
                log.info("******* Customers stored in DB Size :: {}", customers.size());
                log.info("******* Customers stored in DB :: {}", customers);
            }
        };
    }

}
