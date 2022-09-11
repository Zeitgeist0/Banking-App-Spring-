package com.b1.bankingapp1.dao;

import com.b1.bankingapp1.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TemplateAccountDao implements Dao<Account> {

  private JdbcTemplate jdbcTemplate;

  public TemplateAccountDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Account> findAll() {
    String sql = "SELECT * FROM accounts";

    List<Account> accounts = jdbcTemplate.query(
      sql,
      new BeanPropertyRowMapper(Account.class));

    return accounts;
  }

  @Override
  public boolean deleteById(long id) {
    if(getOne(id) == null) {
      return false;
    } else {
      jdbcTemplate.update("delete FROM accounts WHERE ID = ?",id);
      return true;
    }
  }



  public Account getOne(long id) {
    Account account = jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE id=?",
      new BeanPropertyRowMapper<>(Account.class),
      id
    );
    return account;
  }


  public void update(Account account) {
    jdbcTemplate.update("update accounts set number = ?, balance = ?, currency = ?, customer_id = ? WHERE id = ?",
      account.getNumber(),
      account.getBalance(),
      account.getCurrency(),
      account.getCustomerId(),
      account.getId()
    );
  }

  @Override
  public void save(Account account) {
    String sql = "INSERT INTO accounts (currency, customer_id , number, balance) " +
      "VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sql,
      account.getCurrency(),
      account.getCustomerId(),
    account.getNumber(),
    account.getBalance());
  }

  @Override
  public boolean delete(Account account) {
   if(getOne(account.getId()) == null) {
     return false;
   } else {
     jdbcTemplate.update("delete FROM accounts WHERE ID = ?",account.getId());
     return true;
   }
  }

  @Override
  public void deleteAll(List<Account> entities) {

  }

  @Override
  public void saveAll(List<Account> entities) {

  }

  public List<Account> getByCustomerId (Long customerId) {
    String sql = "SELECT * FROM accounts WHERE customer_id = ?";

    List<Account> accounts = jdbcTemplate.query(
      sql,
      new BeanPropertyRowMapper(Account.class),customerId);

    return accounts;
  }

  public  Account getByNumber (String number) {
    Account account = jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE number=?",
      new BeanPropertyRowMapper<>(Account.class),
      number
    );
    return account;
  }

public void setFunds (String number, Double funds) {
  jdbcTemplate.update("UPDATE accounts SET balance = ? WHERE number = ?",
    funds,
    number);
}
}