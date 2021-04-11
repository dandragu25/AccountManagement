package ro.dan.dragu.account.management.dal.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ro.dan.dragu.account.management.dal.entity.Account;
import ro.dan.dragu.account.management.dal.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {JpaTestConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@DataJpaTest(showSql = false)
public class TransactionRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Test
    public void testSave() {
        Account account =TestUtils.createAccount(1);
        accountRepository.save(account);

        Transaction transaction = TestUtils.createTransaction(account);
        transactionRepository.save(transaction);


        assertEquals(1, transactionRepository.findAll()
                .size());

    }

    @Test
    public void testFindByAccountAndTimestampAfter() {
        Account account =TestUtils.createAccount(1);
        accountRepository.save(account);

        Transaction transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(1));
        transactionRepository.save(transaction);

        transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(2));
        transactionRepository.save(transaction);

        transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(3));
        transactionRepository.save(transaction);

        transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(4));
        transactionRepository.save(transaction);


        List<Transaction> result  = transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now());
        assertEquals(0, result.size());

        result  = transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now().minusHours(1).minusMinutes(1));
        assertEquals(1, result.size());

        result  = transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now().minusHours(2).minusMinutes(1));
        assertEquals(2, result.size());

        result  = transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now().minusHours(3).minusMinutes(1));
        assertEquals(3, result.size());

        result  = transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now().minusHours(4).minusMinutes(1));
        assertEquals(4, result.size());
    }

    @Test
    public void testFindByAccountAndTimestampAfterFilterByAccount() {
        Account account =TestUtils.createAccount(1);
        accountRepository.save(account);

        Transaction transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(1));
        transactionRepository.save(transaction);

        transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(2));
        transactionRepository.save(transaction);

        transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(3));
        transactionRepository.save(transaction);

        transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(4));
        transactionRepository.save(transaction);

        account =TestUtils.createAccount(2);
        accountRepository.save(account);

        transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(1));
        transactionRepository.save(transaction);

        transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(2));
        transactionRepository.save(transaction);

        transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(3));
        transactionRepository.save(transaction);

        transaction = TestUtils.createTransaction(account, LocalDateTime.now().minusHours(4));
        transactionRepository.save(transaction);

        assertEquals(8, transactionRepository.findAll().size());



        List<Transaction> result  = transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now());
        assertEquals(0, result.size());

        result  = transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now().minusHours(1).minusMinutes(1));
        assertEquals(1, result.size());

        result  = transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now().minusHours(2).minusMinutes(1));
        assertEquals(2, result.size());

        result  = transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now().minusHours(3).minusMinutes(1));
        assertEquals(3, result.size());

        result  = transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now().minusHours(4).minusMinutes(1));
        assertEquals(4, result.size());
    }
}
