package miu.edu.bank.Service;

import java.util.Date;
import javax.security.auth.login.AccountNotFoundException;
import miu.edu.bank.DTO.AccountDTO;
import miu.edu.bank.Model.Account;
import miu.edu.bank.Model.Transaction;
import miu.edu.bank.Repository.AccountAdapter;
import miu.edu.bank.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

  @Autowired
  private BankRepository bankRepository;

  public void createAccount(AccountDTO accountDTO) {
    Account account = AccountAdapter.fromAccountDTOToAccount(accountDTO);
    this.bankRepository.save(account);
  }

  public void removeAccount(String accountNumber)
    throws AccountNotFoundException {
    Account account = this.bankRepository.findByAccountNumber(accountNumber);
    if (account == null) {
      throw new AccountNotFoundException(
        "Account with account no. " + accountNumber + " not found"
      );
    }
    this.bankRepository.delete(account);
  }

  public AccountDTO getAccount(String accountNumber)
    throws AccountNotFoundException {
    Account account = this.bankRepository.findByAccountNumber(accountNumber);
    if (account == null) {
      throw new AccountNotFoundException(
        "Account with account no. " + accountNumber + " not found"
      );
    }
    return AccountAdapter.fromAccountToAccountDTO(account);
  }

  public void deposit(String accountNumber, double amount)
    throws AccountNotFoundException {
    Transaction transaction = new Transaction(amount, new Date());
    Account account = AccountAdapter.fromAccountDTOToAccount(
      this.getAccount(accountNumber)
    );
    account.addTransactions(transaction);
    this.bankRepository.save(account);
  }

  public void withdraw(String accountNumber, double amount)
    throws AccountNotFoundException {
    Transaction transaction = new Transaction(-amount, new Date());
    Account account = AccountAdapter.fromAccountDTOToAccount(
      this.getAccount(accountNumber)
    );
    account.addTransactions(transaction);
    this.bankRepository.save(account);
  }
}
