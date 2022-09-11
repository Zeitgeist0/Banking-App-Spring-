package com.app.banking.hibernate.bankingapphibernate.resource;


import com.app.banking.hibernate.bankingapphibernate.domain.Customer;
import com.app.banking.hibernate.bankingapphibernate.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RestCustomerController {
  private final CustomerService customerService;

  @GetMapping("/{id}")
  public Customer getById(@PathVariable("id") String userId) {
    return customerService.getOne(Long.parseLong(userId));
  }
  @GetMapping("/all")
  public List<Customer> getAll() {
    return customerService.findAll();
  }


  @PostMapping()
  public void save(@RequestBody Customer customer) {
    customerService.save(customer);
  }

  @PutMapping ()
  public void update(@RequestBody Customer customer) {
  customerService.update(customer);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String customerId) {
    customerService.deleteById(Long.parseLong(customerId));
  }

}
