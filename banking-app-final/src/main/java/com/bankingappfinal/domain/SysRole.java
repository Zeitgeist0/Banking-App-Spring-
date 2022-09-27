package com.bankingappfinal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sys_roles")
//@NamedEntityGraph(
//  name = "rolesFull",
//  attributeNodes = {
//    @NamedAttributeNode(value = "sys_users"),
//  }
//)
public class SysRole extends AbstractEntity {

  @Column(name = "name")
  private String name;

  @ManyToMany( mappedBy = "sysRoles", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<SysUser>  sysUsers;

  @Override
  public String toString() {
    return "SysRole{" +
      "name='" + name + '\'' +
      '}';
  }
}
