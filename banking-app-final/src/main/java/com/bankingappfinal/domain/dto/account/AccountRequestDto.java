package com.bankingappfinal.domain.dto.account;

import com.bankingappfinal.domain.Currency;
import com.bankingappfinal.resource.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {

  private Integer id;

  @NotNull
  @Enumerated
  private Currency currency;
  @NotNull
  private String number = UUID.randomUUID().toString();
  @NotNull
  @Min(value = 0L, message = "The value must be positive")
  private Double balance;


  @NotNull
  private Integer customerId;

  @JsonSerialize(using = CustomDateSerializer.class)
  private Date creationDate;

  @Override
  public String toString() {
    return "AccountRequestDto{" +
      "id=" + id +
      ", currency='" + currency + '\'' +
      ", number='" + number + '\'' +
      ", balance=" + balance +
      ", customerId=" + customerId +
      ", createdOn=" + creationDate +
      '}';
  }
}
