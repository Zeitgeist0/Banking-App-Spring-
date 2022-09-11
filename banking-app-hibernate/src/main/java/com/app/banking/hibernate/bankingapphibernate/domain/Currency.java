package com.app.banking.hibernate.bankingapphibernate.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


public enum Currency {
  USD("USD"),
  EUR("EUR"),
  UAH("UAH"),
  CHF("CHF"),
  GBP("GBP");


  private final String currency;

  Currency(String currency) {
    this.currency = currency;
  }

  public String getCurrency() {
    return currency;
  }


}
