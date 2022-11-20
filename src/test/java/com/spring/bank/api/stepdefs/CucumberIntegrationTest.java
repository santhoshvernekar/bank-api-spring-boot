package com.spring.bank.api.stepdefs;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
@RunWith(Cucumber.class)
@AutoConfigureMockMvc
@CucumberOptions(features = "src/test/resources/features",
        tags = "@sanity",
        plugin = {"pretty", "html:target/cucumber/"})

public class CucumberIntegrationTest {
}
