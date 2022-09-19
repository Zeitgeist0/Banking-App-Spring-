package com.bankingappfinal.dao;

import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.Customer;
import com.bankingappfinal.domain.Employer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EmployerJpaRepository extends JpaRepository<Employer, Integer>, JpaSpecificationExecutor<Employer> {




  @EntityGraph("employersFull")
  Set<Employer> findAllByIdIn(Set<Integer> employerIds);

}
