package com.bankingappfinal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "employers")
@Getter
@Setter
@NamedEntityGraph(
  name = "employersFull",
  attributeNodes = {
    @NamedAttributeNode(value = "customers"),
  }
)
public class Employer extends AbstractEntity {
  @NotNull
  @Column(name = "name")
  private String name;
  @NotNull
  @Column(name = "address")
  private String address;


  @ManyToMany(fetch = FetchType.LAZY )
  private Set<Customer> customers = new LinkedHashSet<>();

  @Override
  public String toString() {
    return "Employer{" +
      "id=" + this.getId() +
      "number='" + name + '\'' +
      ", currency='" + address + '\'' +
//      ", customers=" + customers +
      '}';
  }
}
