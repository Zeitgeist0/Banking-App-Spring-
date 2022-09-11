package com.app.banking.hibernate.bankingapphibernate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name")
 private String name;
  @Column(name = "email")
 private String email;
  @Column(name = "age")
 private Integer age;

  @OneToMany(mappedBy = "customer")
  private Set<Account> accounts = new LinkedHashSet<>();





  @Override
  public String toString() {
    return "Customer{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", age=" + age +
      '}';
  }
}
