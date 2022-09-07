package com.b1.bankingapp1.resource;

import com.b1.bankingapp1.domain.Account;
import com.b1.bankingapp1.domain.Customer;
import com.b1.bankingapp1.service.CustomerService;
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

  @GetMapping("/andAccounts")
  public List<Customer> getAllCustomersAndAccounts() {
    return customerService.findAllCustomersAndAccounts();
  }

  @PostMapping()
  public void save(@RequestBody Customer customer) {
    customerService.save(customer);
  }

  @PutMapping ()
  public boolean update(@RequestBody Customer customer) {
 return customerService.update(customer);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String customerId) {
    customerService.deleteById(Long.parseLong(customerId));
  }

}
