package com.bankingappfinal.service.mapper.employer;

import com.bankingappfinal.domain.Customer;
import com.bankingappfinal.domain.Employer;
import com.bankingappfinal.domain.dto.customer.CustomerRequestDto;
import com.bankingappfinal.domain.dto.employer.EmployerRequestDto;
import com.bankingappfinal.service.AccountService;
import com.bankingappfinal.service.CustomerService;
import com.bankingappfinal.service.EmployerService;
import com.bankingappfinal.service.mapper.DtoMapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class EmployerRequestMapper extends DtoMapperFacade<Employer, EmployerRequestDto> {
  private CustomerService customerService;
  public EmployerRequestMapper() {
    super(Employer.class, EmployerRequestDto.class);
  }
  @Autowired
  public EmployerRequestMapper(CustomerService customerService) {
    this();
    this.customerService = customerService;
  }
  @Override
  protected void decorateEntity(Employer entity, EmployerRequestDto dto) {
    Set<Integer> customerIds = dto.getCustomerIds();
    Set<Customer> customers = customerService.findAllByEmployersIdIn(customerIds);
    entity.setCustomers(customers);

  }

}
