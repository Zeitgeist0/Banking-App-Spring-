package com.bankingappfinal.service.mapper.employer;

import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.Customer;
import com.bankingappfinal.domain.Employer;
import com.bankingappfinal.domain.dto.account.AccountResponseDto;
import com.bankingappfinal.domain.dto.customer.CustomerRequestDto;
import com.bankingappfinal.domain.dto.customer.CustomerResponseDto;
import com.bankingappfinal.domain.dto.employer.EmployerResponseDto;
import com.bankingappfinal.service.mapper.DtoMapperFacade;
import com.bankingappfinal.service.mapper.account.AccountResponseMapper;
import com.bankingappfinal.service.mapper.customer.CustomerResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployerResponseMapper extends DtoMapperFacade<Employer, EmployerResponseDto> {
  private CustomerResponseMapper customerResponseMapper;
  public EmployerResponseMapper() {
    super(Employer.class, EmployerResponseDto.class);
  }
  @Autowired
  public EmployerResponseMapper(CustomerResponseMapper customerResponseMapper) {
    this();
    this.customerResponseMapper = customerResponseMapper;
  }
  @Override
  protected void decorateDto(EmployerResponseDto dto, Employer entity) {
Set<CustomerResponseDto> customerSet = entity.getCustomers().stream().map(customerResponseMapper::convertToDto).collect(Collectors.toSet());
dto.setCustomers(customerSet);
  }
}
