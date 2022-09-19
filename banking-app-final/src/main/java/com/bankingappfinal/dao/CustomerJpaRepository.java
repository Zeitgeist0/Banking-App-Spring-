package com.bankingappfinal.dao;


import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.Customer;
import com.bankingappfinal.domain.Employer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface CustomerJpaRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {
  @EntityGraph("customersFull")
  Set<Customer> findAllByEmployersIdIn(Set<Integer> customersId);


  @EntityGraph("customersFull")
  Set<Customer> findAllByEmployersIn(Set<Employer> employers);

}
