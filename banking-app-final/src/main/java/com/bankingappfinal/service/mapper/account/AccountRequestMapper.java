package com.bankingappfinal.service.mapper.account;

import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.Customer;
import com.bankingappfinal.domain.dto.account.AccountRequestDto;
import com.bankingappfinal.service.CustomerService;
import com.bankingappfinal.service.mapper.DtoMapperFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountRequestMapper  extends DtoMapperFacade<Account, AccountRequestDto> {
  private   CustomerService customerService;
  public AccountRequestMapper() {
    super(Account.class, AccountRequestDto.class);
  }
  @Autowired
  public AccountRequestMapper( CustomerService customerService) {
    this();
    this.customerService = customerService;
  }
  @Override
  protected void decorateEntity(Account entity, AccountRequestDto dto) {
  Integer customerId = dto.getCustomerId();
    Optional<Customer> customer = customerService.findById(customerId);
   customer.ifPresent(entity::setCustomer);


  }

}
