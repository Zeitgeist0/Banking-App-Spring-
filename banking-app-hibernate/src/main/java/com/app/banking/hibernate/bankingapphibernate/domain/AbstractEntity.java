package com.app.banking.hibernate.bankingapphibernate.domain;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}
