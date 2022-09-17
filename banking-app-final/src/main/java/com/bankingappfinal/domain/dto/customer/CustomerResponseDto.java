package com.bankingappfinal.domain.dto.customer;

import com.bankingappfinal.domain.dto.account.AccountResponseDto;
import com.bankingappfinal.domain.dto.employer.EmployerResponseDto;
import com.bankingappfinal.resource.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class CustomerResponseDto {
  @NotNull
  private Integer id;
  @NotNull
  private String name;
  @NotNull
  private String password;

  private String email;

  private Integer age;

  private String phoneNumber;

  private Set<String> employers = new LinkedHashSet<>();


  private Set<AccountResponseDto> accounts = new LinkedHashSet<>();

  @JsonSerialize(using = CustomDateSerializer.class)
  private Date creationDate;
  @JsonSerialize(using = CustomDateSerializer.class)
  private Date lastModifiedDate;



}
