package com.b1.bankingapp1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  private Long id;
 private String name;
 private String email;
 private Integer age;
 private List<Account> accounts;

  public Customer(String name, String email, Integer age) {
    this.name = name;
    this.email = email;
    this.age = age;
  }
  public Customer(long id) {
    this.id = id;
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
    return "Customer{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", age=" + age +
      ", accounts=" + accounts +
      '}';
  }
}
