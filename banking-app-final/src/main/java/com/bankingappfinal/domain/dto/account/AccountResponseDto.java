package com.bankingappfinal.domain.dto.account;

import com.bankingappfinal.resource.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  private String currency;

  @NotNull
  private String number;

  @NotNull
  private Double balance;


  @NotNull
  private Integer customerId;

  @JsonSerialize(using = CustomDateSerializer.class)
  private Date createdOn;

  @JsonSerialize(using = CustomDateSerializer.class)
  private Date modifiedOn;


}
