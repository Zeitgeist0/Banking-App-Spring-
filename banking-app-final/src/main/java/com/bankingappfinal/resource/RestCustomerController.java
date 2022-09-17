package com.bankingappfinal.resource;



import com.bankingappfinal.domain.Customer;
import com.bankingappfinal.service.CustomerService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RestCustomerController {
  private final CustomerService customerService;

  @GetMapping("/getById")
  public Optional<Customer> getById(@RequestBody ObjectNode objectNode) {
    Integer customerId = objectNode.get("customerId").asInt();
    return customerService.findById(customerId);
  }
  @GetMapping("/all")
  public List<Customer> getAll() {
    return customerService.findAll();
  }


  @PostMapping()
  public void save(@RequestBody Customer customer) {
    customerService.save(customer);
  }


  @DeleteMapping("/deleteById")
  public void deleteById(@RequestBody ObjectNode objectNode) {
    Integer customerId = objectNode.get("customerId").asInt();
    customerService.deleteById(customerId);
  }

}
