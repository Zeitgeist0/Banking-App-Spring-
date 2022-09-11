package com.b1.bankingapp1.domain;

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
