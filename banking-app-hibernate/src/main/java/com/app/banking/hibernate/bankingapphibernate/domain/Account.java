package com.app.banking.hibernate.bankingapphibernate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "number", length = 20)
  private String number;
//  @Enumerated(EnumType.STRING)
@Column(name = "currency", length = 20)
  private String currency;
  @Column(name = "balance")
  private Double balance;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonIgnore
  @JoinColumn(name = "customer_id")
 private Customer customer;



  @Override
  public String toString() {
    return "Account{" +
      "id=" + id +
      ", number='" + number + '\'' +
      ", currency=" + currency +
      ", balance=" + balance +
      ", customer=" + customer +
      '}';
  }
}