package com.bankingappfinal.resource;


import com.bankingappfinal.domain.Employer;
import com.bankingappfinal.service.EmployerService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employers")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RestEmployerController {
  private final EmployerService employerService;

  @GetMapping("/getById")
  public Optional<Employer> getById(@RequestBody ObjectNode objectNode) {
    Integer employerId = objectNode.get("employerId").asInt();
    return employerService.findById(employerId);
  }
  @GetMapping("/all")
  public List<Employer> getAll() {
    return employerService.findAll();
  }


  @PostMapping()
  public void save(@RequestBody Employer employer) {
    employerService.save(employer);
  }



  @DeleteMapping("/deleteById")
  public void deleteById(@RequestBody ObjectNode objectNode) {
    Integer employerId = objectNode.get("employerId").asInt();
    employerService.deleteById(employerId);
  }
}
