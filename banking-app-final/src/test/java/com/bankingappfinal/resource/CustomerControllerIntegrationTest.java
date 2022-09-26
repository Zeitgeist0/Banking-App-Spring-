package com.bankingappfinal.resource;

import com.bankingappfinal.dao.AccountJpaRepository;
import com.bankingappfinal.dao.CustomerJpaRepository;
import com.bankingappfinal.dao.CustomerPagingRepository;
import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.Currency;
import com.bankingappfinal.domain.dto.account.AccountResponseDto;
import com.bankingappfinal.service.mapper.account.AccountResponseMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.yml")
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CustomerControllerIntegrationTest {
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CustomerJpaRepository customerJpaRepository;
  @Autowired
  private AccountJpaRepository accountJpaRepository;
  @Autowired
  private CustomerPagingRepository customerPagingRepository;

  @Test
  public void test_GetById() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/getById")
        .contentType("application/json")
        .content(
          """
          {
              "customerId": "1"
          }
          """
        ))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.is("Alia Chadwick")))
      .andExpect(MockMvcResultMatchers.jsonPath("email", Matchers.is("abc@gmail.com")))
      .andExpect(MockMvcResultMatchers.jsonPath("age", Matchers.is(21)))
      .andExpect(MockMvcResultMatchers.jsonPath("phoneNumber", Matchers.is( "+380961528025")))
      .andExpect(MockMvcResultMatchers.jsonPath("employers", Matchers.is(List.of("Samsung", "Amazon", "Facebook"))))
      .andExpect(MockMvcResultMatchers.jsonPath("$.accounts[0].customerId", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.accounts[0].number", Matchers.is("678ce8f1-7fbc-495f-afb4-d1f469afdcd4")))
    ;
  }

  @Test
  public void test_GetAllPageable() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/allPageable/2/0"))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Alia Chadwick")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("abc@gmail.com")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].age", Matchers.is(21)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber", Matchers.is( "+380961528025")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].employers", Matchers.is(List.of("Samsung", "Amazon", "Facebook"))))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].accounts[0].customerId", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].accounts[0].number", Matchers.is("678ce8f1-7fbc-495f-afb4-d1f469afdcd4")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Maxim Zimmerman")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("abc@email.com")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].age", Matchers.is(35)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber", Matchers.is( "+380965478025")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].employers", Matchers.is(List.of("Facebook"))))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].accounts[0].customerId", Matchers.is(2)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].accounts[1].customerId", Matchers.is(2)))
    ;
  }

  @Test
  public void test_GetAll() throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/all").contentType("application/json"))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Alia Chadwick")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("abc@gmail.com")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].age", Matchers.is(21)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber", Matchers.is( "+380961528025")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].employers", Matchers.is(List.of("Samsung", "Amazon", "Facebook"))))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].accounts[0].customerId", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].accounts[0].number", Matchers.is("678ce8f1-7fbc-495f-afb4-d1f469afdcd4")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Maxim Zimmerman")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("abc@email.com")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].age", Matchers.is(35)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber", Matchers.is( "+380965478025")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].employers", Matchers.is(List.of("Facebook"))))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].accounts[0].customerId", Matchers.is(2)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].accounts[1].customerId", Matchers.is(2)))
    ;
  }

  @Test
  public void test_Save_AndGet() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.post("/customers")
        .contentType("application/json")
        .content(
          """
            {
                 "name" : "Test",
                    "password" : "2442",
                    "email" : "domain@etestmail.com",
                    "age" : 18,
                    "phoneNumber" : "0961528080"
            }
            """
        ))
      .andExpect(status().isOk())
    ;
    this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/getById")
        .contentType("application/json")
        .content(
          """
          {
              "customerId": "11"
          }
          """
        ))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.is(11)))
      .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.is("Test")))
      .andExpect(MockMvcResultMatchers.jsonPath("email", Matchers.is("domain@etestmail.com")))
      .andExpect(MockMvcResultMatchers.jsonPath("age", Matchers.is(18)))
      .andExpect(MockMvcResultMatchers.jsonPath("phoneNumber", Matchers.is( "0961528080")))
    ;
  }

  @Test
  public void test_DeleteById() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/customers/deleteById")
        .contentType("application/json")
        .content(
          """
          {
              "customerId": "10"
          }
          """
        ))
      .andExpect(status().isOk())
    ;
    assertTrue(customerJpaRepository.findById(10).isEmpty() );

  }
}
