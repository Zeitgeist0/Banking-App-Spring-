package com.app.banking.hibernate.bankingapphibernate.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Employer extends AbstractEntity {
  @Column(name = "name")
  private String name;
  @Column(name = "address")
  private String address;

  @JsonIgnore
  @ManyToMany(mappedBy = "employers")
  private Set<Customer> customers = new LinkedHashSet<>();

  @Override
  public String toString() {
    return "Employer{" +
      "id=" + this.getId() +
      "number='" + name + '\'' +
      ", currency='" + address + '\'' +
      ", customers=" + customers +
      '}';
  }
}
