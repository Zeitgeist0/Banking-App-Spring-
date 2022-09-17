package com.bankingappfinal.domain.dto.account;

import com.bankingappfinal.resource.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
  @NotNull
  private Integer id;

  @NotNull
  @Size(min = 3, message = "Currency must have 3 letters")
  private String currency;
  @NotNull
  private String number;
  @NotNull
  @Min(value = 0L, message = "The value must be positive")
  private Double balance;


  @NotNull
  private Integer customerId;

  @JsonSerialize(using = CustomDateSerializer.class)
  private Date createdOn;

  @Override
  public String toString() {
    return "AccountRequestDto{" +
      "id=" + id +
      ", currency='" + currency + '\'' +
      ", number='" + number + '\'' +
      ", balance=" + balance +
      ", customerId=" + customerId +
      ", createdOn=" + createdOn +
      '}';
  }
}
