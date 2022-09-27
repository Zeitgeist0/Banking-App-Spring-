package com.bankingappfinal.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sys_users")
//@NamedEntityGraph(
//  name = "usersFull",
//  attributeNodes = {
//    @NamedAttributeNode(value = "sys_roles"),
//  }
//)
public class SysUser extends AbstractEntity {

  @Column(name = "name", length = 36, nullable = false)
  private String name;

  @Column(name = "password", length = 128, nullable = false)
  private String password;

  @Column(name = "enabled", length = 1, nullable = false)
  private boolean enabled;

  @ManyToMany (fetch = FetchType.EAGER)
  private Set<SysRole> sysRoles = new HashSet<>();

  @Override
  public String toString() {
    return "SysUser{" +
      "name='" + name + '\'' +
      ", password='" + password + '\'' +
      ", enabled=" + enabled +
      '}';
  }
}
