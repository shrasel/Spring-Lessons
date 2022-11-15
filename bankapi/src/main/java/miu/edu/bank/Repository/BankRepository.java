package miu.edu.bank.Repository;

import miu.edu.bank.Model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends MongoRepository<Account, String> {
  Account findByAccountNumber(String accountNumber);
}
