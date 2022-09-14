package com.app.banking.hibernate.bankingapphibernate.resource;

import com.app.banking.hibernate.bankingapphibernate.domain.Customer;
import com.app.banking.hibernate.bankingapphibernate.domain.Employer;
import com.app.banking.hibernate.bankingapphibernate.service.EmployerService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employers")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RestEmployerController {
  private final EmployerService employerService;

  @GetMapping("/getById")
  public Employer getById(@RequestBody ObjectNode objectNode) {
    Long employerId = objectNode.get("employerId").asLong();
    return employerService.getOne(employerId);
  }
  @GetMapping("/all")
  public List<Employer> getAll() {
    return employerService.findAll();
  }


  @PostMapping()
  public void save(@RequestBody Employer employer) {
    employerService.save(employer);
  }

  @PutMapping ()
  public void update(@RequestBody Employer employer) {
    employerService.update(employer);
  }

  @DeleteMapping("/deleteById")
  public void deleteById(@RequestBody ObjectNode objectNode) {
    String employerId = objectNode.get("employerId").asText();
    employerService.deleteById(Long.parseLong(employerId));
  }
}
