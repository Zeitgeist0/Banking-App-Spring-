package com.bankingappfinal.service.mapper.account;

import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.dto.account.AccountRequestDto;
import com.bankingappfinal.domain.dto.account.AccountResponseDto;
import com.bankingappfinal.service.mapper.DtoMapperFacade;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class AccountResponseMapper extends DtoMapperFacade<Account, AccountResponseDto> {
  public AccountResponseMapper() {
    super(Account.class, AccountResponseDto.class);
  }

  @Override
  protected void decorateDto(AccountResponseDto dto, Account entity) {

    dto.setCustomerId(entity.getCustomer().getId());

  }
}
