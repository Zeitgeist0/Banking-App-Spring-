package com.app.banking.hibernate.bankingapphibernate.service;


import com.app.banking.hibernate.bankingapphibernate.dao.HibernateAccountDao;
import com.app.banking.hibernate.bankingapphibernate.dao.HibernateCustomerDao;
import com.app.banking.hibernate.bankingapphibernate.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class AccountService implements Service<Account> {

  private  HibernateAccountDao accountDao;


  public AccountService(HibernateAccountDao accountDao ) {
    this.accountDao = accountDao;
  }



public void  update(Account account ){
  accountDao.update(account);
}

  @Override
  public void save(Account account) {
   accountDao.save(account);
  }

  @Override
  public boolean delete(Account account) {
    return accountDao.delete(account);
  }

  @Override
  public void deleteAll(List<Account> accounts) {
accountDao.deleteAll(accounts);
  }

  @Override
  public void saveAll(List<Account> accounts) {
    accountDao.saveAll(accounts);
  }

  @Transactional(readOnly = true)
public Account getByNumber (String number) {
    return accountDao.getByNumber(number);
}

public void addFunds (String number , Double funds) {
    Account account = getByNumber(number);
    if (account == null) {
      return;
    } else {
     Double balance = account.getBalance() + funds;
     accountDao.setFunds(number , balance);
    }
}
  public void withdrawFunds (String number , Double funds) {
    Account account = getByNumber(number);
    if (account == null || (account.getBalance() - funds) < 0) {
      return;
    } else {
      Double balance = account.getBalance() - funds;
      accountDao.setFunds(number , balance);
    }
  }
  public void transferFunds (String fromWhere , String toWhere, Double funds) {
    Account fromWhichAccount = getByNumber(fromWhere);
    Account toWhichAccount = getByNumber(toWhere);
    if (fromWhichAccount == null ||
      toWhichAccount == null ||
      (fromWhichAccount.getBalance() - funds) < 0) {
      return;
    } else {
      accountDao.setFunds(fromWhere , fromWhichAccount.getBalance() - funds);
      accountDao.setFunds(toWhere, toWhichAccount.getBalance() + funds);
    }
  }

  public void deleteByNumber (String number) {
    Long accountId = getByNumber(number).getId();

    accountDao.deleteById(accountId);

  }
  @Override
  @Transactional(readOnly = true)
  public List<Account> findAll() {
    return accountDao.findAll();
  }

  @Override
  public boolean deleteById(long id) {
    return accountDao.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Account getOne(long id) {
    return accountDao.getOne(id);
  }
}
