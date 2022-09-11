package com.app.banking.hibernate.bankingapphibernate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account extends AbstractEntity {


  @Type(type="org.hibernate.type.PostgresUUIDType")
  @Column(name = "number", unique = true)
  @GeneratedValue(generator = "hibernate-uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String number = UUID.randomUUID().toString();

//  @Enumerated(EnumType.STRING)
@Column(name = "currency")
  private String currency;
  @Column(name = "balance")
  private Double balance;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
 private Customer customer;

  @Override
  public String toString() {
    return "Account{" +
      "id=" + this.getId() +
      ", number=" + number +
      ", currency='" + currency + '\'' +
      ", balance=" + balance +
      ", customer=" + customer +
      '}';
  }
}