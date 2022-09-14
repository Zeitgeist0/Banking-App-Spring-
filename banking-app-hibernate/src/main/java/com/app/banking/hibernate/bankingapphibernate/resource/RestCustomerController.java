package com.app.banking.hibernate.bankingapphibernate.resource;


import com.app.banking.hibernate.bankingapphibernate.domain.Customer;
import com.app.banking.hibernate.bankingapphibernate.service.CustomerService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RestCustomerController {
  private final CustomerService customerService;

  @GetMapping("/getById")
  public Customer getById(@RequestBody ObjectNode objectNode) {
    Long customerId = objectNode.get("customerId").asLong();
    return customerService.getOne(customerId);
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

  @DeleteMapping("/deleteById")
  public void deleteById(@RequestBody ObjectNode objectNode) {
    String customerId = objectNode.get("customerId").asText();
    customerService.deleteById(Long.parseLong(customerId));
  }

}
