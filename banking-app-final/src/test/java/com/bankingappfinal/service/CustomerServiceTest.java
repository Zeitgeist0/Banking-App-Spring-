package com.bankingappfinal.service;

import com.bankingappfinal.dao.CustomerJpaRepository;
import com.bankingappfinal.dao.CustomerPagingRepository;
import com.bankingappfinal.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @Mock
  CustomerJpaRepository customerJpaRepository;
@Mock
  CustomerPagingRepository customerPagingRepository;
  @InjectMocks
  CustomerService customerService;


  @Test
  public void test_GetAllPageable() {
    Customer customer = new Customer();
    when(customerPagingRepository.findAll(any(Pageable.class)))
      .thenReturn(new PageImpl<>(List.of(customer)));
    List<Customer> customers = customerService.getAllPageable(1, 1);
    assertEquals(customer, customers.get(0));

  }
}
