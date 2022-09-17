package com.bankingappfinal.dao;


import com.bankingappfinal.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {


}
