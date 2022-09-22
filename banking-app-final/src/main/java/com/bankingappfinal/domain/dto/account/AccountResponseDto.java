package com.bankingappfinal.domain.dto.account;

import com.bankingappfinal.domain.Currency;
import com.bankingappfinal.resource.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

  @NotNull
  private Integer id;

  @NotNull
  @Enumerated
  private Currency currency;

  @NotNull
  private String number;

  @NotNull
  private Double balance;


  @NotNull
  private Integer customerId;

  @JsonSerialize(using = CustomDateSerializer.class)
  private Date creationDate;

  @JsonSerialize(using = CustomDateSerializer.class)
  private Date lastModifiedDate;

  @Override
  public String toString() {
    return "AccountResponseDto{" +
      "id=" + id +
      ", currency='" + currency + '\'' +
      ", number='" + number + '\'' +
      ", balance=" + balance +
      ", customerId=" + customerId +
      ", createdOn=" + creationDate +
      ", modifiedOn=" + lastModifiedDate +
      '}';
  }
}
