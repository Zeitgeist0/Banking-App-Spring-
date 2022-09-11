package com.app.banking.hibernate.bankingapphibernate.service;

import com.app.banking.hibernate.bankingapphibernate.dao.Dao;
import com.app.banking.hibernate.bankingapphibernate.dao.HibernateAccountDao;
import com.app.banking.hibernate.bankingapphibernate.dao.HibernateCustomerDao;
import com.app.banking.hibernate.bankingapphibernate.domain.Account;
import com.app.banking.hibernate.bankingapphibernate.domain.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Transactional
public class CustomerService implements Service<Customer> {


  private HibernateCustomerDao customerDao;

  public CustomerService(HibernateCustomerDao hibernateCustomerDao) { // List<UserDao> dao - inject all deps
    this.customerDao = hibernateCustomerDao;
  }




  @Override
  public void save(Customer obj) {
    customerDao.save(obj);
  }

  @Override
  public boolean delete(Customer obj) {
    return customerDao.delete(obj);
  }

  @Override
  public void deleteAll(List<Customer> entities) {

  }

  @Override
  public void saveAll(List<Customer> entities) {

  }
public void update (Customer customer) {
    customerDao.update(customer);
}

//  public List<Customer> findAllCustomersAndAccounts() {
//
//   return  customerDao.findAll().stream().map(customer -> {
//     List<Account> customerAccounts = accountDao.getByCustomerId(customer.getId());
//     customer.setAccounts(customerAccounts);
//     return customer;
//   }).collect(Collectors.toList());
//  }

  @Override
  public List<Customer> findAll () {
    return customerDao.findAll();
  }

  @Override
  public boolean deleteById(long id) {
    return customerDao.deleteById(id);
  }

  @Override
  public Customer getOne(long id) {

//     List<Account> customerAccounts = accountDao.getByCustomerId(id);
//     customer.setAccounts(customerAccounts);
     return customerDao.getOne(id);

  }
}
