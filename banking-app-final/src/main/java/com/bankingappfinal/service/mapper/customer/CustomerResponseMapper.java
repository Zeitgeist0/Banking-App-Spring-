package com.bankingappfinal.service.mapper.customer;

import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.Customer;
import com.bankingappfinal.domain.Employer;
import com.bankingappfinal.domain.dto.account.AccountResponseDto;
import com.bankingappfinal.domain.dto.customer.CustomerRequestDto;
import com.bankingappfinal.domain.dto.customer.CustomerResponseDto;
import com.bankingappfinal.domain.dto.employer.EmployerResponseDto;
import com.bankingappfinal.service.CustomerService;
import com.bankingappfinal.service.mapper.DtoMapperFacade;
import com.bankingappfinal.service.mapper.account.AccountResponseMapper;
import com.bankingappfinal.service.mapper.employer.EmployerResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerResponseMapper extends DtoMapperFacade<Customer, CustomerResponseDto> {
  private AccountResponseMapper accountResponseMapper;

  public CustomerResponseMapper() {
    super(Customer.class, CustomerResponseDto.class);
  }
  @Autowired
  public CustomerResponseMapper( AccountResponseMapper accountResponseMapper) {
    this();
    this.accountResponseMapper = accountResponseMapper;

  }
  @Override
  protected void decorateDto(CustomerResponseDto dto, Customer entity) {
  Set<AccountResponseDto> accountSet =  entity.getAccounts().
    stream().map(accountResponseMapper::convertToDto).collect(Collectors.toSet());

Set<String> employerSet = entity.getEmployers().
  stream().map(Employer::getName).collect(Collectors.toSet());
dto.setAccounts(accountSet);
dto.setEmployers(employerSet);

  }


}
