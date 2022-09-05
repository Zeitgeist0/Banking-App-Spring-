package com.b1.bankingapp1.service;

import com.b1.bankingapp1.dao.TemplateAccountDao;
import com.b1.bankingapp1.dao.TemplateCustomerDao;
import com.b1.bankingapp1.domain.Account;
import com.b1.bankingapp1.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class CustomerService implements Service<Customer>{

  private TemplateCustomerDao customerDao;
  @Autowired
  private TemplateAccountDao accountDao;

  public CustomerService(TemplateCustomerDao customerDao) { // List<UserDao> dao - inject all deps
    this.customerDao = customerDao;
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
public boolean update (Customer customer) {
   return customerDao.update(customer);
}
  @Override
  public List<Customer> findAll() {
    return customerDao.findAll();
  }

  @Override
  public boolean deleteById(long id) {
    return customerDao.deleteById(id);
  }

  @Override
  public Customer getOne(long id) {
     Customer customer = customerDao.getOne(id);
     List<Account> customerAccounts = accountDao.getByCustomerId(id);
     customer.setAccounts(customerAccounts);
     return customer;

  }
}
