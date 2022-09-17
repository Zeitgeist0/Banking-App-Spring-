package com.bankingappfinal.dao;


import com.bankingappfinal.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AccountJpaRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

  @EntityGraph("accountsFull")
  Set<Account> findAccountsByCustomerId(Integer id);
  @EntityGraph("accountsFull")
  Optional<Account> findAccountByNumber(String accountNumber);

   void deleteAccountByNumber(String accountNumber);

}
