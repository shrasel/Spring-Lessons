package miu.edu.bank.Repository;

import miu.edu.bank.DTO.AccountDTO;
import miu.edu.bank.Model.Account;

public class AccountAdapter {

  public static AccountDTO fromAccountToAccountDTO(Account account) {
    return new AccountDTO(
      account.getAccountNumber(),
      account.getAccountHolder(),
      account.getBalance()
    );
  }

  public static Account fromAccountDTOToAccount(AccountDTO accountDTO) {
    return new Account(
      accountDTO.getAccountNumber(),
      accountDTO.getAccountHolder(),
      accountDTO.getBalance()
    );
  }
}
