package com.bankingappfinal.service;


import com.bankingappfinal.dao.CustomerJpaRepository;
import com.bankingappfinal.dao.CustomerPagingRepository;
import com.bankingappfinal.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Transactional
public class CustomerService implements Service<Customer> {

private final CustomerJpaRepository customerJpaRepository;
private final CustomerPagingRepository customerPagingRepository;
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

  public Set<Customer> findAllByIdIn (Set<Integer> customersIds) {
    return customerJpaRepository.findAllByIdIn(customersIds);
  }



  public List<Customer> getAllPageable(int size, int pageNumber) {
    Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
    Pageable pageable = PageRequest.of(pageNumber, size, sort);

    Page<Customer> customerPage = customerPagingRepository.findAll(pageable);
    return customerPage.toList();
  }


}
