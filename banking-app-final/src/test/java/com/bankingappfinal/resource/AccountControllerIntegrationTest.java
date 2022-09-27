package com.bankingappfinal.resource;

import com.bankingappfinal.dao.AccountJpaRepository;
import com.bankingappfinal.domain.Currency;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.yml")
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class AccountControllerIntegrationTest {
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AccountJpaRepository accountJpaRepository;

  @Test
  public void test_GetAll() throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts/all").contentType("application/json"))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].balance", Matchers.is(2455.0)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].number", Matchers.is("678ce8f1-7fbc-495f-afb4-d1f469afdcd4")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].currency", Matchers.is("USD")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerId", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].balance", Matchers.is(242200.0)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].number", Matchers.is("593c74fe-ba9e-441f-8567-1a551e0962fc")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].currency", Matchers.is("CHF")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].customerId", Matchers.is(2)))
    ;
  }

  @Test
  public void test_GetById() throws Exception {

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
      .andExpect(MockMvcResultMatchers.jsonPath("balance", Matchers.is(2455.0)))
      .andExpect(MockMvcResultMatchers.jsonPath("number", Matchers.is("678ce8f1-7fbc-495f-afb4-d1f469afdcd4")))
      .andExpect(MockMvcResultMatchers.jsonPath("currency", Matchers.is("USD")))
      .andExpect(MockMvcResultMatchers.jsonPath("customerId", Matchers.is(1)))
    ;
  }

  @Test
  public void test_DeleteById() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/deleteById")
        .contentType("application/json")
        .content(
          """
          {
              "accountId": "6"
          }
          """
        ))
      .andExpect(status().isOk())
    ;
   assertEquals(accountJpaRepository.findById(6), Optional.empty() );
  }

  @Test
  public void test_DeleteByNumber() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/deleteByNumber")
        .contentType("application/json")
        .content(
          """
          {
              "accountNumber": "678c66f1-7fbc-495f-afb4-d1f469afdcd4"
          }
          """
        ))
      .andExpect(status().isOk())
    ;
    assertTrue(accountJpaRepository.findAccountByNumber("678c66f1-7fbc-495f-afb4-d1f469afdcd4").isEmpty() );

  }

  @Test
  public void test_Save() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
        .contentType("application/json")
        .content(
          """
            {
                  "currency": 1,
                    "customerId" : 1,
                   "balance" : 1000
            }
            """
        ))
      .andExpect(status().isOk())
    ;
    assertEquals(accountJpaRepository.getById(16).getBalance(), 1000.0);
    assertEquals(accountJpaRepository.getById(16).getCurrency(), Currency.EUR);
    assertEquals(accountJpaRepository.getById(16).getCustomer().getId(), 1);
  }

  @Test
  public void test_AddFunds() throws Exception {
    assertEquals(accountJpaRepository.getById(3).getBalance(), 10000.0);
    this.mockMvc.perform(MockMvcRequestBuilders.put("/accounts/addFunds")
        .contentType("application/json")
        .content(
          """
            {
               "number" : "3d3fe3b4-83ba-4756-a92e-36873d3c4ae7",
                   "funds" : 10000
            }
            """
        ))
      .andExpect(status().isOk())
    ;
    assertEquals(accountJpaRepository.getById(3).getBalance(), 20000.0);

  }
  @Test
  public void test_WithdrawFunds_Successfully() throws Exception {
    assertEquals(accountJpaRepository.findAccountByNumber("7885555b-7367-4b8b-962a-af1085f36ae9")
      .get().getBalance(), 100000.0);
    this.mockMvc.perform(MockMvcRequestBuilders.put("/accounts/withdrawFunds")
        .contentType("application/json")
        .content(
          """
            {
               "number" : "7885555b-7367-4b8b-962a-af1085f36ae9",
                   "funds" : 50000
            }
            """
        ))
      .andExpect(status().isOk())
    ;
    assertEquals(50000.0, accountJpaRepository.findAccountByNumber("7885555b-7367-4b8b-962a-af1085f36ae9")
      .get().getBalance());

  }
  @Test
  public void test_WithdrawFunds_Unsuccessfully() throws Exception {
    assertEquals(accountJpaRepository.findAccountByNumber("e4d55552-fea4-4544-b40d-0106a47383ce")
      .get().getBalance(), 100000.0);
    this.mockMvc.perform(MockMvcRequestBuilders.put("/accounts/withdrawFunds")
        .contentType("application/json")
        .content(
          """
            {
               "number" : "e4d55552-fea4-4544-b40d-0106a47383ce",
                   "funds" : 5000000
            }
            """
        ))
      .andExpect(status().isOk())
    ;
    assertEquals(100000.0, accountJpaRepository.findAccountByNumber("e4d55552-fea4-4544-b40d-0106a47383ce")
      .get().getBalance());

  }

  @Test
  public void test_TransferFunds_Successfully() throws Exception {
    assertEquals(accountJpaRepository.findAccountByNumber("48466555b-7367-4b8b-9621-af108ef36ae9")
      .get().getBalance(), 100000.0);
    assertEquals(accountJpaRepository.findAccountByNumber("38466556b-7367-4b8b-9621-af1085g36ae9")
      .get().getBalance(), 100000.0);
    this.mockMvc.perform(MockMvcRequestBuilders.put("/accounts/transferFunds")
        .contentType("application/json")
        .content(
          """
            {
              "originAccountNumber" : "48466555b-7367-4b8b-9621-af108ef36ae9",
                  "destinationAccountNumber" : "38466556b-7367-4b8b-9621-af1085g36ae9",
                  "funds": "50000"
            }
            """
        ))
      .andExpect(status().isOk())
    ;
    assertEquals(50000.0, accountJpaRepository.findAccountByNumber("48466555b-7367-4b8b-9621-af108ef36ae9")
      .get().getBalance());
    assertEquals(150000.0, accountJpaRepository.findAccountByNumber("38466556b-7367-4b8b-9621-af1085g36ae9")
      .get().getBalance());
  }

  @Test
  public void test_TransferFunds_Unsuccessfully() throws Exception {
    assertEquals(accountJpaRepository.findAccountByNumber("28466555b-7347-4b8b-9621-af10ggf36ae9")
      .get().getBalance(), 100000.0);
    assertEquals(accountJpaRepository.findAccountByNumber("18466555b-7347-4b8b-962a-af1085f36ae9")
      .get().getBalance(), 100000.0);
    this.mockMvc.perform(MockMvcRequestBuilders.put("/accounts/transferFunds")
        .contentType("application/json")
        .content(
          """
            {
              "originAccountNumber" : "28466555b-7347-4b8b-9621-af10ggf36ae9",
                  "destinationAccountNumber" : "18466555b-7347-4b8b-962a-af1085f36ae9",
                  "funds": "2500000"
            }
            """
        ))
      .andExpect(status().isOk())
    ;
    assertEquals(100000.0, accountJpaRepository.findAccountByNumber("48466555b-7367-4b8b-9621-af108ef36ae9")
      .get().getBalance());
    assertEquals(100000.0, accountJpaRepository.findAccountByNumber("38466556b-7367-4b8b-9621-af1085g36ae9")
      .get().getBalance());
  }
}
