package com.bankingappfinal.resource;

import com.bankingappfinal.dao.AccountJpaRepository;
import com.bankingappfinal.domain.Account;


import com.bankingappfinal.domain.Currency;
import com.bankingappfinal.domain.Customer;
import com.bankingappfinal.service.AccountService;
import com.bankingappfinal.service.mapper.account.AccountRequestMapper;
import com.bankingappfinal.service.mapper.account.AccountResponseMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@RunWith(SpringRunner.class)
@WebMvcTest(RestAccountController.class)
public class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

@MockBean
 ObjectNode objectNode;

  @MockBean
  AccountService accountService;

  @MockBean
  AccountJpaRepository accountJpaRepository;

  @Autowired
  AccountRequestMapper accountRequestMapper;

  @Autowired
  AccountResponseMapper accountResponseMapper;
  @TestConfiguration
  public static class TestConfig {
    @Bean
    public AccountResponseMapper accountResponseMapper() {
      return new AccountResponseMapper();
    }
    @Bean
    public AccountRequestMapper accountRequestMapper() {
      return new AccountRequestMapper();
    }
  }
  Account account1;
  Customer customer1;
  Account account2;
  Customer customer2;
  List<Account> accounts;
  @BeforeEach
  void init() {
     account1 = new Account();
    account1.setId(1);
    account1.setBalance(1000.0);
    account1.setNumber("1111");
    account1.setCurrency(Currency.EUR);
    customer1 = new Customer();
    customer1.setId(1);
    account1.setCustomer(customer1);

     account2 = new Account();
    account2.setId(2);
    account2.setBalance(2000.0);
    account2.setNumber("2222");
    account2.setCurrency(Currency.UAH);
     customer2 = new Customer();
    customer2.setId(2);
    account2.setCustomer(customer2);

    accounts = List.of(account1,account2);
  }

  @AfterEach
  void teardown() {
   account1 = null;
   account2 = null;
   customer1 = null;
   customer2 = null;
   accounts = null;
  }

  @Test
  public void testGetAll() throws Exception {
    when(accountService.findAll()).thenReturn(accounts);
    this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts/all").contentType("application/json"))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].balance", Matchers.is(1000.0)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].number", Matchers.is("1111")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].currency", Matchers.is("EUR")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerId", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].balance", Matchers.is(2000.0)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].number", Matchers.is("2222")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].currency", Matchers.is("UAH")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].customerId", Matchers.is(2)))
    ;
  }
  @Test
  public void testGetById() throws Exception {
    when(accountService.findById(1)).thenReturn(Optional.of(account1));


    this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts/getById")
        .contentType("application/json")
        .content(
          """
          {
              "accountId": "1"
          }
          """
        ))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("balance", Matchers.is(1000.0)))
      .andExpect(MockMvcResultMatchers.jsonPath("number", Matchers.is("1111")))
      .andExpect(MockMvcResultMatchers.jsonPath("currency", Matchers.is("EUR")))
      .andExpect(MockMvcResultMatchers.jsonPath("customerId", Matchers.is(1)))
    ;
  }


}
