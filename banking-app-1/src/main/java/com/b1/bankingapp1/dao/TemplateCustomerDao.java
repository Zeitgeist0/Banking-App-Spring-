package com.b1.bankingapp1.dao;

import com.b1.bankingapp1.domain.Account;
import com.b1.bankingapp1.domain.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TemplateCustomerDao implements Dao<Customer> {
  private JdbcTemplate jdbcTemplate;

  public TemplateCustomerDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
  @Override
  public void save(Customer customer) {
    String sql = "INSERT INTO customers (name, email, age) " +
      "VALUES (?, ?)";
    jdbcTemplate.update(sql,
     customer.getName(),
      customer.getEmail(),
      customer.getAge());
  }

  @Override
  public boolean delete(Customer customer) {
    if(getOne(customer.getId()) == null) {
      return false;
    } else {
      jdbcTemplate.update("delete FROM customers WHERE ID = ?",customer.getId());
      return true;
    }
  }
  public boolean update(Customer customer) {
    if (getOne(customer.getId()) == null) {
      return false;
    } else {
      jdbcTemplate.update("update customers set name = ?, age = ?, email = ? WHERE id = ?",
        customer.getName(),
        customer.getAge(),
        customer.getEmail(),
        customer.getId()
      );
      return true;
    }

  }
  @Override
  public void deleteAll(List<Customer> entities) {

  }

  @Override
  public void saveAll(List<Customer> entities) {

  }

  @Override
  public List<Customer> findAll() {
    String sql = "SELECT * FROM customers";

    List<Customer> customers = jdbcTemplate.query(
      sql,
      new BeanPropertyRowMapper(Customer.class));

    return customers;
  }

  @Override
  public boolean deleteById(long id) {
    if(getOne(id) == null) {
      return false;
    } else {
      jdbcTemplate.update("delete FROM customers WHERE ID = ?",id);
      return true;
    }
  }

  @Override
  public Customer getOne(long id) {
    Customer customer = jdbcTemplate.queryForObject("SELECT * FROM customers WHERE id=?",
      new BeanPropertyRowMapper<>(Customer.class),
      id
    );
    return customer;
  }
}
