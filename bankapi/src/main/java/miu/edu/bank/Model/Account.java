package miu.edu.bank.Model;

import java.util.ArrayList;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {

  @NotNull
  private String accountNumber;

  @NotNull
  @Size(min = 2, max = 40)
  private String accountHolder;

  @NotNull
  private double balance;

  private final Collection<Transaction> transactions;

  public Account(String accountNumber, String accountHolder, double balance) {
    this.accountNumber = accountNumber;
    this.accountHolder = accountHolder;
    this.balance = balance;
    this.transactions = new ArrayList<>();
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public Collection<Transaction> getTransactions() {
    return transactions;
  }

  public void addTransactions(Transaction transaction) {
    this.balance += transaction.getAmount();
    this.transactions.add(transaction);
  }
}
