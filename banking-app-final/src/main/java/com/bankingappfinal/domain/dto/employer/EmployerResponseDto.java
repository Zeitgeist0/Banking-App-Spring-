package com.bankingappfinal.domain.dto.employer;

import com.bankingappfinal.domain.dto.customer.CustomerResponseDto;
import com.bankingappfinal.resource.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerResponseDto {
  @NotNull
  private Integer id;
  @NotNull
  @Size(min = 3, message = "Name must have 3 letters")
  private String name;
  @NotNull
  @Size(min = 3, message = "Address must have 3 letters")
  private String address;

  private Set<CustomerResponseDto> customers = new LinkedHashSet<>();

  @JsonSerialize(using = CustomDateSerializer.class)
  private Date creationDate;
  @JsonSerialize(using = CustomDateSerializer.class)
  private Date lastModifiedDate;

  @Override
  public String toString() {
    return "EmployerResponseDto{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", address='" + address + '\'' +
      ", customers=" + customers +
      ", creationDate=" + creationDate +
      ", lastModifiedDate=" + lastModifiedDate +
      '}';
  }
}
