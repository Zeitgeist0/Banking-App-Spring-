package com.b1.bankingapp1.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  private Long id;
  private String number;
  private String currency;
  private Double balance;
 private Long customerId;

  public Account(String currency, Long customer) {
    this.currency = currency;
    this.customerId = customer;
    this.number = UUID.randomUUID().toString();
    this.balance = 0.0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Account account = (Account) o;

    return getId().equals(account.getId());
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
  }

  @Override
  public String toString() {
    return "Account{" +
      "id=" + id +
      ", number='" + number + '\'' +
      ", currency=" + currency +
      ", balance=" + balance +
      ", customer=" + customerId +
      '}';
  }
}