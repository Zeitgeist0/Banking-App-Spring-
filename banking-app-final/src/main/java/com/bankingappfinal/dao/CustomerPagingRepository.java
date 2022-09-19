package com.bankingappfinal.dao;

import com.bankingappfinal.domain.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerPagingRepository extends PagingAndSortingRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {
}
