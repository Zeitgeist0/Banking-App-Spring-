package com.bankingappfinal.dao;


import com.bankingappfinal.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerJpaRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {
}
