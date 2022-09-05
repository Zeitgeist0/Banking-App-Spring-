package com.b1.bankingapp1.resource;

import com.b1.bankingapp1.domain.Account;
import com.b1.bankingapp1.domain.Customer;
import com.b1.bankingapp1.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class RestAccountController {
  private final AccountService accountService;

  @GetMapping("/all")
  public List<Account> getAll() {
    return accountService.findAll();
  }

  @GetMapping("/{id}")
  public Account getById(@PathVariable("id") String accountId) {

    return accountService.getOne(Long.parseLong(accountId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String accountId) {
    accountService.deleteById(Long.parseLong(accountId));
  }

  @PostMapping()
  public void save(@RequestBody Account account) {
Account newAccount = new Account(account.getCurrency(), account.getCustomerId());
    accountService.save(newAccount);
  }

  @PutMapping("/addFunds/{number}/{funds}")
  public void addFunds(@PathVariable("number") String number, @PathVariable("funds") Double funds) {

    accountService.addFunds(number,funds);
  }
  @PutMapping("/withdrawFunds/{number}/{funds}")
  public void withdrawFunds(@PathVariable("number") String number, @PathVariable("funds") Double funds) {
    accountService.withdrawFunds(number,funds);
  }

  @PutMapping("/transferFunds/{fromWhere}/{toWhere}/{funds}")
  public void transferFunds(@PathVariable("fromWhere") String fromWhere,
                            @PathVariable("toWhere") String toWhere,
                            @PathVariable("funds") Double funds) {
    accountService.transferFunds(fromWhere,toWhere, funds);
  }
}
