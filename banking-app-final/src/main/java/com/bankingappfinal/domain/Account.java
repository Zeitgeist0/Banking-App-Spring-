package com.bankingappfinal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
  @NotNull
  @Column(name = "number")
  private String number = UUID.randomUUID().toString();


//  @Enumerated(EnumType.STRING)
@NotNull
@Column(name = "currency")
@Enumerated
  private Currency currency;
  @NotNull
  @Column(name = "balance")
  private Double balance;
@NotNull
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