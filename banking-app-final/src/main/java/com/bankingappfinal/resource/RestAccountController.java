package com.bankingappfinal.resource;




import com.bankingappfinal.domain.Account;
import com.bankingappfinal.domain.dto.account.AccountRequestDto;
import com.bankingappfinal.domain.dto.account.AccountResponseDto;
import com.bankingappfinal.service.AccountService;
import com.bankingappfinal.service.mapper.account.AccountRequestMapper;
import com.bankingappfinal.service.mapper.account.AccountResponseMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@CrossOrigin(origins = {"http://localhost:3000"})
@Slf4j
public class RestAccountController {
  private final AccountService accountService;
private final AccountResponseMapper accountResponseMapper;
private final AccountRequestMapper accountRequestMapper;
private final SimpMessagingTemplate simpMessagingTemplate;

  @GetMapping("/all")
  public List<AccountResponseDto> getAll() {
    return accountService.findAll().stream().
    map(accountResponseMapper::convertToDto).
      collect(Collectors.toList());
  }

  @GetMapping("/getById")
  public Optional<AccountResponseDto> getById(@RequestBody ObjectNode objectNode) {

    Integer accountId = objectNode.get("accountId").asInt();
    return  accountService.findById(accountId).map(accountResponseMapper::convertToDto);

  }

  @DeleteMapping("/deleteById")
  public void deleteById(@RequestBody ObjectNode objectNode) {

    Integer accountId = objectNode.get("accountId").asInt();
    accountService.deleteById(accountId);
  }
  @DeleteMapping("/deleteByNumber")
  public void deleteByNumber(@RequestBody ObjectNode objectNode) {
    String accountNumber = objectNode.get("accountNumber").asText();
    accountService.deleteByNumber(accountNumber);
  }
  @PostMapping()
  public void save(@Valid @RequestBody AccountRequestDto account) {
 Account newAccount = accountRequestMapper.convertToEntity(account);
    accountService.save(newAccount);
  }
  @PutMapping("/addFunds")
  public void addFunds(@RequestBody ObjectNode objectNode) {
    String number = objectNode.get("number").asText();
    Double funds = objectNode.get("funds").asDouble();

    accountService.addFunds(number,funds);
    StringBuilder sb = new StringBuilder();
    sb.append(funds);
    sb.append(" funds were added to the following account number: ");
    sb.append(number);
    simpMessagingTemplate.convertAndSend( "/queue/user", sb.toString());
  }

  @PutMapping("/withdrawFunds")
  public void withdrawFunds(@RequestBody ObjectNode objectNode) {
    String number = objectNode.get("number").asText();
    Double funds = objectNode.get("funds").asDouble();
    accountService.withdrawFunds(number,funds);
  }

  @PutMapping("/transferFunds")
  public void transferFunds(@RequestBody ObjectNode objectNode) {
    String originAccountNumber = objectNode.get("originAccountNumber").asText();
    String destinationAccountNumber = objectNode.get("destinationAccountNumber").asText();
    Double funds = objectNode.get("funds").asDouble();

    accountService.transferFunds(originAccountNumber,destinationAccountNumber, funds);
  }


}
