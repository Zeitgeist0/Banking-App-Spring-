package com.app.banking.hibernate.bankingapphibernate.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "employers")
@Getter
@Setter
public class Employer {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", length = 20)
  private String number;
  @Column(name = "address", length = 20)
  private String currency;
  @ManyToMany(mappedBy = "employers")
  private Set<Customer> customers = new LinkedHashSet<>();
}
