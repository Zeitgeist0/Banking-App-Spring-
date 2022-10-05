package com.bankingappfinal.service;



import com.bankingappfinal.dao.AccountJpaRepository;
import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Transactional
public class AccountService implements Service<Account> {

private final AccountJpaRepository accountJpaRepository;

public boolean addFunds (String number , Double funds) {
    Optional<Account> account = accountJpaRepository.findAccountByNumber(number);
return account.map(currentAccount -> {
  currentAccount.setBalance(currentAccount.getBalance() + funds);
  accountJpaRepository.save(currentAccount);
  return true;
}).orElse(false);
}
  public boolean withdrawFunds (String number , Double funds) {
    Optional<Account> account = accountJpaRepository.findAccountByNumber(number);
//    account.ifPresent(currentAccount -> {
//     if( (currentAccount.getBalance() - funds) > 0 ) {
//       currentAccount.setBalance(currentAccount.getBalance() - funds);
//       accountJpaRepository.save(currentAccount);
//     }
//    });
    return account.map(currentAccount -> {
      if( (currentAccount.getBalance() - funds) > 0 ) {
        currentAccount.setBalance(currentAccount.getBalance() - funds);
        accountJpaRepository.save(currentAccount);
        return true;
      } else {
        return false;
      }
    }).orElse(false);
  }

  public boolean transferFunds (String originAccountNumber , String destinationAccountNumber, Double funds) {
    Optional<Account> originAccount = accountJpaRepository.findAccountByNumber(originAccountNumber);
    Optional<Account>  destinationAccount = accountJpaRepository.findAccountByNumber(destinationAccountNumber);
    boolean enoughFunds = originAccount.filter(a -> (a.getBalance() - funds) > 0).isPresent();
    if( destinationAccount.isPresent() && enoughFunds) {
      Double originBalance = originAccount.get().getBalance();
      Double destinationBalance = destinationAccount.get().getBalance();
      originAccount.get().setBalance(originBalance - funds);
      destinationAccount.get().setBalance(destinationBalance + funds);
      accountJpaRepository.save(originAccount.get());
      accountJpaRepository.save(destinationAccount.get());
      return true;
    }
     return false;
  }

  public void deleteByNumber (String number) {
    accountJpaRepository.deleteAccountByNumber(number);
  }



  @Override
  public Optional<Account> findById(Integer id) {
    return accountJpaRepository.findById(id);
  }

  @Override
  public List<Account> findAll() {
    return accountJpaRepository.findAll();
  }

  @Override
  public void save(Account account) {
 accountJpaRepository.save(account);
  }

  @Override
  public void saveAll(List<Account> accounts) {
    accountJpaRepository.saveAll(accounts);
  }

  @Override
  public void delete(Account account) {
accountJpaRepository.delete(account);
  }

  @Override
  public void deleteById(Integer id) {
accountJpaRepository.deleteById(id);
  }

  @Override
  public void deleteAll(List<Account> accounts) {
accountJpaRepository.deleteAll(accounts);
  }


}
