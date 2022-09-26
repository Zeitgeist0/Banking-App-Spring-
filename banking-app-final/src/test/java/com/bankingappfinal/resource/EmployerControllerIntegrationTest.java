package com.bankingappfinal.resource;

import com.bankingappfinal.dao.EmployerJpaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.yml")
@AutoConfigureMockMvc
@Transactional
public class EmployerControllerIntegrationTest {
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  EmployerJpaRepository employerJpaRepository;

  @Test
  public void test_GetById() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/employers/getById")
        .contentType("application/json")
        .content(
          """
          {
              "employerId": "1"
          }
          """
        ))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.is("Samsung")))
      .andExpect(MockMvcResultMatchers.jsonPath("address", Matchers.is("Samsung street")))
    ;
  }

  @Test
  public void test_GetAll() throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders.get("/employers/all").contentType("application/json"))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Samsung")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].address", Matchers.is("Samsung street")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is( "Facebook")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].address", Matchers.is("Facebook street")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("Google")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[2].address", Matchers.is("Google street")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[3].id", Matchers.is(4)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[3].name", Matchers.is("Apple")))
      .andExpect(MockMvcResultMatchers.jsonPath("$[3].address", Matchers.is("Apple street")))


    ;
  }

  @Test
  public void test_Save_AndGet() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.post("/employers")
        .contentType("application/json")
        .content(
          """
            {
               "name" : "Tesla",
                  "address": "Tesla street",
                 "customerIds" : [1]
            }
            """
        ))
      .andExpect(status().isOk())
    ;
    this.mockMvc.perform(MockMvcRequestBuilders.get("/employers/getById")
        .contentType("application/json")
        .content(
          """
          {
              "employerId": "7"
          }
          """
        ))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.is( 7)))
      .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.is("Tesla")))
      .andExpect(MockMvcResultMatchers.jsonPath("address", Matchers.is("Tesla street")))
      .andExpect(MockMvcResultMatchers.jsonPath("customers[0].id", Matchers.is(1)))
    ;
  }

  @Test
  public void test_DeleteById() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/employers/deleteById")
        .contentType("application/json")
        .content(
          """
          {
              "employerId": "5"
          }
          """
        ))
      .andExpect(status().isOk())
    ;
    assertTrue(employerJpaRepository.findById(5).isEmpty() );

  }
}
