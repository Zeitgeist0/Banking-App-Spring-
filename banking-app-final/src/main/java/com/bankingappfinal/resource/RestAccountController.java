package com.bankingappfinal.resource;




import com.bankingappfinal.domain.Account;
import com.bankingappfinal.service.AccountService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
  public Optional<Account> getById(@RequestBody ObjectNode objectNode) {
    Integer accountId = objectNode.get("accountId").asInt();
    return accountService.findById(accountId);
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

  @PutMapping("/transferFunds")
  public void transferFunds(@RequestBody ObjectNode objectNode) {
    String fromWhere = objectNode.get("fromWhere").asText();
    String toWhere = objectNode.get("toWhere").asText();
    Double funds = objectNode.get("funds").asDouble();

    accountService.transferFunds(fromWhere,toWhere, funds);
  }
}
