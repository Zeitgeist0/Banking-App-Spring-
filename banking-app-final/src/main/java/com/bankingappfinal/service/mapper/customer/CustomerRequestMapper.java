package com.bankingappfinal.service.mapper.customer;

import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.Customer;
import com.bankingappfinal.domain.Employer;
import com.bankingappfinal.domain.dto.account.AccountRequestDto;
import com.bankingappfinal.domain.dto.customer.CustomerRequestDto;
import com.bankingappfinal.service.AccountService;
import com.bankingappfinal.service.CustomerService;
import com.bankingappfinal.service.EmployerService;
import com.bankingappfinal.service.mapper.DtoMapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Service
public class CustomerRequestMapper extends DtoMapperFacade<Customer, CustomerRequestDto> {
  private  EmployerService employerService;


  public CustomerRequestMapper() {
    super(Customer.class, CustomerRequestDto.class);
  }
  @Autowired
  public CustomerRequestMapper( EmployerService employerService, AccountService accountService) {
    this();
    this.employerService = employerService;

  }
  @Override
  protected void decorateEntity(Customer entity, CustomerRequestDto dto) {
    Set<Integer> employerIds = dto.getEmployerIds();
    Set<Employer> employers = employerService.findAllByIdIn(employerIds);
    entity.setEmployers(employers);

  }
}
