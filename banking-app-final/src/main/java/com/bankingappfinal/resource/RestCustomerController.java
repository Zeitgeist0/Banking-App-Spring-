package com.bankingappfinal.resource;



import com.bankingappfinal.domain.Customer;
import com.bankingappfinal.domain.dto.customer.CustomerRequestDto;
import com.bankingappfinal.domain.dto.customer.CustomerResponseDto;
import com.bankingappfinal.service.CustomerService;
import com.bankingappfinal.service.mapper.customer.CustomerRequestMapper;
import com.bankingappfinal.service.mapper.customer.CustomerResponseMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class RestCustomerController {
  private final CustomerService customerService;
  private final CustomerResponseMapper customerResponseMapper;
  private final CustomerRequestMapper customerRequestMapper;

  @GetMapping("/getById")
  public Optional<CustomerResponseDto> getById(@RequestBody ObjectNode objectNode) {
    Integer customerId = objectNode.get("customerId").asInt();
    return customerService.findById(customerId).map(customerResponseMapper::convertToDto);
  }
  @GetMapping("/allPageable/{size}/{pageNumber}")
  public List<CustomerResponseDto> getAllPageable(@PathVariable("size") Integer size,
                                                  @PathVariable("pageNumber") Integer pageNumber) {

    return customerService.getAllPageable(size, pageNumber).stream().map(customerResponseMapper::convertToDto).collect(Collectors.toList());
  }
  @GetMapping("/all")
  public List<CustomerResponseDto> getAll() {
    return customerService.findAll().stream().map(customerResponseMapper::convertToDto).collect(Collectors.toList());
  }

  @PostMapping()
  public void save(@RequestBody CustomerRequestDto customer) {
    Customer newCustomer = customerRequestMapper.convertToEntity(customer);
    customerService.save(newCustomer);
  }


  @DeleteMapping("/deleteById")
  public void deleteById(@RequestBody ObjectNode objectNode) {
    Integer customerId = objectNode.get("customerId").asInt();
    customerService.deleteById(customerId);
  }


}
