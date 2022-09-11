package com.app.banking.hibernate.bankingapphibernate.resource;

import com.app.banking.hibernate.bankingapphibernate.domain.Customer;
import com.app.banking.hibernate.bankingapphibernate.domain.Employer;
import com.app.banking.hibernate.bankingapphibernate.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employers")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RestEmployerController {
  private final EmployerService employerService;

  @GetMapping("/{id}")
  public Employer getById(@PathVariable("id") String employerId) {
    return employerService.getOne(Long.parseLong(employerId));
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

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String employerId) {
    employerService.deleteById(Long.parseLong(employerId));
  }
}
