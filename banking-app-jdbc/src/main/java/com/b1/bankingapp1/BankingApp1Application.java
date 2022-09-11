package com.b1.bankingapp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BankingApp1Application implements ApplicationRunner {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public static void main(String[] args) {
    SpringApplication.run(BankingApp1Application.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

  }
}
