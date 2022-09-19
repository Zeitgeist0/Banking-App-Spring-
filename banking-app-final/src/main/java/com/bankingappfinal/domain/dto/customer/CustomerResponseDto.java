package com.bankingappfinal.domain.dto.customer;

import com.bankingappfinal.domain.dto.account.AccountResponseDto;
import com.bankingappfinal.domain.dto.employer.EmployerResponseDto;
import com.bankingappfinal.resource.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
  @NotNull
  private Integer id;
  @NotNull
  private String name;


  private String email;

  private Integer age;

  private String phoneNumber;

  private Set<String> employers = new LinkedHashSet<>();


  private Set<AccountResponseDto> accounts = new LinkedHashSet<>();

  @JsonSerialize(using = CustomDateSerializer.class)
  private Date creationDate;
  @JsonSerialize(using = CustomDateSerializer.class)
  private Date lastModifiedDate;

  @Override
  public String toString() {
    return "CustomerResponseDto{" +
      "id=" + id +
      ", name='" + name + '\'' +

      ", email='" + email + '\'' +
      ", age=" + age +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", employers=" + employers +
      ", accounts=" + accounts +
      ", creationDate=" + creationDate +
      ", lastModifiedDate=" + lastModifiedDate +
      '}';
  }
}
