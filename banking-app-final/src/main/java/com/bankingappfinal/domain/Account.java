package com.bankingappfinal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "accounts")
@Getter
@Setter
@NamedEntityGraph(
  name = "accountsFull",
  attributeNodes = {
    @NamedAttributeNode(value = "customer"),

  }
)
public class Account extends AbstractEntity {

  @Column(name = "number")
  private String number = UUID.randomUUID().toString();


//  @Enumerated(EnumType.STRING)
@Column(name = "currency")
  private String currency;
  @Column(name = "balance")
  private Double balance;

  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id" )
 private Customer customer;

  @Override
  public String toString() {
    return "Account{" +
      "id=" + this.getId() +
      ", number=" + number +
      ", currency='" + currency + '\'' +
      ", balance=" + balance +
//     ", customer=" + customer +
      '}';
  }
}