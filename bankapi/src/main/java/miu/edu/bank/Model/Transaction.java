package miu.edu.bank.Model;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Transaction {

  @NotNull
  private double amount;
  @NotNull
  private Date date;

  public Transaction(double amount, Date date) {
    this.amount = amount;
    this.date = date;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {

    this.amount = amount;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
