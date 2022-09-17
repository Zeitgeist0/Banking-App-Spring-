package com.bankingappfinal.service;


import com.bankingappfinal.dao.CustomerJpaRepository;
import com.bankingappfinal.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class CustomerService implements Service<Customer> {

private final CustomerJpaRepository customerJpaRepository;

  @Override
  public Optional<Customer> findById(Integer id) {

    return customerJpaRepository.findById(id);
  }

  @Override
  public List<Customer> findAll() {
    return customerJpaRepository.findAll();
  }

  @Override
  public void save(Customer customer) {
    customerJpaRepository.save(customer);
  }

  @Override
  public void saveAll(List<Customer> customers) {
customerJpaRepository.saveAll(customers);
  }

  @Override
  public void delete(Customer customer) {
customerJpaRepository.delete(customer);
  }

  @Override
  public void deleteById(Integer id) {

customerJpaRepository.deleteById(id);
  }

  @Override
  public void deleteAll(List<Customer> customers) {
 customerJpaRepository.deleteAll(customers);
  }
}
