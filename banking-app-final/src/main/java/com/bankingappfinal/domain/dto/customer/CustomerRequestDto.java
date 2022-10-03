package com.bankingappfinal.domain.dto.customer;

import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.Employer;
import com.bankingappfinal.domain.dto.account.AccountRequestDto;
import com.bankingappfinal.domain.dto.employer.EmployerRequestDto;
import com.bankingappfinal.resource.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

  private Integer id;
  @NotBlank
  @Size(min = 3, message = "Name must have 3 letters")
  private String name;
  @NotBlank
  @Size(min = 3, message = "Password must have 3 symbols")
  private String password;


//  @Email(message = "Email is not valid",
//    regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\" + \"[^-]" +
//      "[A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")
  @Pattern(regexp = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
  @NotBlank(message = "Email cannot be empty")
  private String email;
@Min(value = 18, message = "You must be over 18")
  private Integer age;
  @Pattern(regexp = "(\\+380|0)[0-9]{9}")
  @NotBlank(message = "Phone cannot be empty")
  private String phoneNumber;

  private Set<Integer> employerIds = new LinkedHashSet<>();


  @JsonSerialize(using = CustomDateSerializer.class)
  private Date creationDate;

  @Override
  public String toString() {
    return "CustomerRequestDto{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", password='" + password + '\'' +
      ", email='" + email + '\'' +
      ", age=" + age +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", employers=" + employerIds +
      ", creationDate=" + creationDate +
      '}';
  }
}
