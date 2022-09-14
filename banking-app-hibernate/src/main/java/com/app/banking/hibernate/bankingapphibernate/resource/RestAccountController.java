package com.app.banking.hibernate.bankingapphibernate.resource;



import com.app.banking.hibernate.bankingapphibernate.domain.Account;
import com.app.banking.hibernate.bankingapphibernate.service.AccountService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RestAccountController {
  private final AccountService accountService;

  @GetMapping("/all")
  public List<Account> getAll() {
    System.out.println(org.hibernate.Version.getVersionString());
    return accountService.findAll();
  }

  @GetMapping("/getById")
  public Account getById(@RequestBody ObjectNode objectNode) {
    String accountId = objectNode.get("accountId").asText();
    return accountService.getOne(Long.parseLong(accountId));
  }

  @DeleteMapping("/deleteById")
  public void deleteById(@RequestBody ObjectNode objectNode) {
    Long accountId = objectNode.get("accountId").asLong();
    accountService.deleteById(accountId);
  }

  @PostMapping()
  public void save(@RequestBody Account account) {

    accountService.save(account);
  }
  @PutMapping("/addFunds")
  public void addFunds(@RequestBody ObjectNode objectNode) {
    String number = objectNode.get("number").asText();
    Double funds = objectNode.get("funds").asDouble();
    accountService.addFunds(number,funds);
  }

  @PutMapping("/withdrawFunds")
  public void withdrawFunds(@RequestBody ObjectNode objectNode) {
    String number = objectNode.get("number").asText();
    Double funds = objectNode.get("funds").asDouble();
    accountService.withdrawFunds(number,funds);
  }

  @PutMapping("/transferFunds/{fromWhere}/{toWhere}/{funds}")
  public void transferFunds(@RequestBody ObjectNode objectNode) {
    String fromWhere = objectNode.get("fromWhere").asText();
    String toWhere = objectNode.get("toWhere").asText();
    Double funds = objectNode.get("funds").asDouble();

    accountService.transferFunds(fromWhere,toWhere, funds);
  }
}
