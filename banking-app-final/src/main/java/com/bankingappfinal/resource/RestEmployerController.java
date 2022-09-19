package com.bankingappfinal.resource;


import com.bankingappfinal.domain.Employer;
import com.bankingappfinal.domain.dto.employer.EmployerRequestDto;
import com.bankingappfinal.domain.dto.employer.EmployerResponseDto;
import com.bankingappfinal.service.EmployerService;
import com.bankingappfinal.service.mapper.employer.EmployerRequestMapper;
import com.bankingappfinal.service.mapper.employer.EmployerResponseMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employers")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RestEmployerController {
  private final EmployerService employerService;
  private final EmployerResponseMapper employerResponseMapper;
  private final EmployerRequestMapper employerRequestMapper;

  @GetMapping("/getById")
  public Optional<EmployerResponseDto> getById(@RequestBody ObjectNode objectNode) {
    Integer employerId = objectNode.get("employerId").asInt();
    return employerService.findById(employerId).map(employerResponseMapper::convertToDto);
  }
  @GetMapping("/all")
  public List<EmployerResponseDto> getAll() {
    return employerService.findAll().stream().map(employerResponseMapper::convertToDto).collect(Collectors.toList());
  }


  @PostMapping()
  public void save(@RequestBody EmployerRequestDto employer) {
    Employer newEmployer = employerRequestMapper.convertToEntity(employer);

    employerService.save(newEmployer);
  }



  @DeleteMapping("/deleteById")
  public void deleteById(@RequestBody ObjectNode objectNode) {
    Integer employerId = objectNode.get("employerId").asInt();
    employerService.deleteById(employerId);
  }
}
