package miu.edu.bank.Controller;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import miu.edu.bank.DTO.AccountDTO;
import miu.edu.bank.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("accounts")
public class BankController {

  @Autowired
  private BankService bankService;

  @PostMapping("/")
  public ResponseEntity<?> createAccount(
    @RequestBody @Valid AccountDTO accountDTO
  ) {
    this.bankService.createAccount(accountDTO);
    return new ResponseEntity<>(accountDTO, HttpStatus.CREATED);
  }

  @DeleteMapping("/{accountNumber}")
  public ResponseEntity<?> removeAccount(
    @PathVariable("accountNumber") String accountNumber
  ) throws AccountNotFoundException {
    this.bankService.removeAccount(accountNumber);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{accountNumber}")
  public ResponseEntity<?> getAccount(
    @PathVariable("accountNumber") String accountNumber
  ) throws AccountNotFoundException {
    AccountDTO accountDTO = this.bankService.getAccount(accountNumber);
    return new ResponseEntity<>(accountDTO, HttpStatus.OK);
  }

  @PostMapping("/deposit")
  public ResponseEntity<?> deposit(String accountNumber, Double amount)
    throws AccountNotFoundException {
    AccountDTO accountDTO = this.bankService.getAccount(accountNumber);
    if (accountDTO == null) throw new AccountNotFoundException(
      "Account with account no. " + accountNumber + " not found"
    );
    this.bankService.deposit(accountNumber, amount);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/withdraw")
  public ResponseEntity<?> withdraw(String accountNumber, Double amount)
    throws AccountNotFoundException {
    AccountDTO accountDTO = this.bankService.getAccount(accountNumber);
    if (accountDTO == null) throw new AccountNotFoundException(
      "Account with account no. " + accountNumber + " not found"
    );
    this.bankService.withdraw(accountNumber, amount);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
