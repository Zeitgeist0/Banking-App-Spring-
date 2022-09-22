package com.bankingappfinal.resource;

import com.bankingappfinal.dao.AccountJpaRepository;
import com.bankingappfinal.domain.Account;
import com.bankingappfinal.service.AccountService;
import com.bankingappfinal.service.mapper.account.AccountRequestMapper;
import com.bankingappfinal.service.mapper.account.AccountResponseMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@WebMvcTest(RestAccountController.class)
public class RestAccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

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

  @BeforeEach
  void init() {
Account account = new Account();
account.setId(1);
account.setBalance(1000.0);
account.setNumber("1111");

  }

  @AfterEach
  void teardown() {

  }


}
