package com.bankingappfinal.service;

import com.bankingappfinal.dao.AccountJpaRepository;
import com.bankingappfinal.domain.Account;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

  @Mock
  AccountJpaRepository accountJpaRepository;
@InjectMocks
  AccountService accountService ;

Account account1;
  String accountNumber1;
  Double balance1;
  Optional<Account> accountOptional1;

Account account2;
String accountNumber2;
Double balance2;
  Optional<Account> accountOptional2;


  Double funds1;
  Double funds2;

  @BeforeEach
  void init() {
     account1 = new Account();
   accountNumber1 = "1111";
     balance1 = 2000.0;
    account1.setNumber(accountNumber1);
    account1.setBalance(balance1);
     accountOptional1 = Optional.of(account1);


    account2 = new Account();
    accountNumber2 = "2222";
    balance2 = 2000.0;
    account2.setNumber(accountNumber2);
    account2.setBalance(balance2);
    accountOptional2 = Optional.of(account2);

    funds1 = 1000.0;
    funds2 = 3000.0;

  }

  @AfterEach
  void teardown() {
    account1 = null;
    accountNumber1 = null;
    balance1 = null;

    accountOptional1 = null;


    account2 = null;
    accountNumber2 = null;
    balance2 = null;
    accountOptional2 =null;

    funds1 = null;
    funds2 = null;
  }

  @Test
  public void test_AddFundsSuccessfully() {

    when(accountJpaRepository.findAccountByNumber(accountNumber1))
      .thenReturn(accountOptional1);

    accountService.addFunds(accountNumber1, funds1);

    Mockito.verify(accountJpaRepository).save(account1);
    assertEquals(3000.0, accountOptional1.get().getBalance());
  }


  @Test
  public void test_FundsNotAdded_AccountNotFound() {


    when(accountJpaRepository.findAccountByNumber(accountNumber1))
      .thenReturn(Optional.empty());

    accountService.addFunds(accountNumber1, funds1);
verify(accountJpaRepository, never()).save(account1);
  }


  @Test
  public void test_WithdrawFundsSuccessfully() {

    when(accountJpaRepository.findAccountByNumber(accountNumber1))
      .thenReturn(accountOptional1);

    accountService.withdrawFunds(accountNumber1, funds1);

    assertEquals(1000, accountOptional1.get().getBalance());
    verify(accountJpaRepository).save(account1);
  }
  @Test
  public void test_WithdrawFundsUnsuccessfully() {

    when(accountJpaRepository.findAccountByNumber(accountNumber1))
      .thenReturn(accountOptional1);

    accountService.withdrawFunds(accountNumber1, funds2);

    verify(accountJpaRepository, never()).save(account1);
  }

  @Test
  public void test_TransferFundsSuccessfully() {

    when(accountJpaRepository.findAccountByNumber(accountNumber1))
      .thenReturn(accountOptional1);
    when(accountJpaRepository.findAccountByNumber(accountNumber2))
      .thenReturn(accountOptional2);
    accountService.transferFunds(accountNumber1, accountNumber2 , funds1);

    verify(accountJpaRepository).save(account1);
    verify(accountJpaRepository).save(account2);
    assertEquals(1000, accountOptional1.get().getBalance());
    assertEquals(3000, accountOptional2.get().getBalance());
  }

  @Test
  public void test_TransferFundsUnsuccessfully_NotEnoughFunds() {

    when(accountJpaRepository.findAccountByNumber(accountNumber1))
      .thenReturn(accountOptional1);
    when(accountJpaRepository.findAccountByNumber(accountNumber2))
      .thenReturn(accountOptional2);
    accountService.transferFunds(accountNumber1, accountNumber2 , funds2);

    verify(accountJpaRepository , never()).save(account1);
    verify(accountJpaRepository, never()).save(account2);
    assertEquals(2000.0, accountOptional1.get().getBalance());
    assertEquals(2000.0, accountOptional2.get().getBalance());
  }
  @Test
  public void test_TransferFundsUnsuccessfully_DestinationAccountNotFound() {

    when(accountJpaRepository.findAccountByNumber(accountNumber1))
      .thenReturn(accountOptional1);
    when(accountJpaRepository.findAccountByNumber(accountNumber2))
      .thenReturn(Optional.empty());
    accountService.transferFunds(accountNumber1, accountNumber2 , funds2);

    verify(accountJpaRepository , never()).save(account1);
    verify(accountJpaRepository, never()).save(account2);
    assertEquals(2000.0, accountOptional1.get().getBalance());
    assertEquals(2000.0, accountOptional2.get().getBalance());
  }
}
