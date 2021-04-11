package ro.dan.dragu.account.management.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dan.dragu.account.management.dal.entity.Account;
import ro.dan.dragu.account.management.dal.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountAndTimestampAfter(Account account, LocalDateTime date);

}
