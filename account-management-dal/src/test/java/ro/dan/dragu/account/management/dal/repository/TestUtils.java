package ro.dan.dragu.account.management.dal.repository;

import ro.dan.dragu.account.management.dal.entity.Account;
import ro.dan.dragu.account.management.dal.entity.AccountRole;
import ro.dan.dragu.account.management.dal.entity.Transaction;

import java.time.LocalDateTime;

public  class TestUtils {

    private TestUtils() {
    }


    public static Account createAccount(int index) {
        Account account = new Account();
        account.setDisabled(false);
        account.setName("account" + index);
        account.setPassword("password" + index);
        account.setRole(AccountRole.ROLE_USER);
        account.setFullName("Account" + index);
        account.setEmail("account" + index + "@mail.com");
        return account;
    }

    public static Transaction createTransaction(Account account) {
        Transaction transaction = new Transaction();
        transaction.setAmount(1.5);
        transaction.setDetails("details");
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setAccount(account);
        return transaction;
    }

    public static Transaction createTransaction(Account account, LocalDateTime timestamp) {
        Transaction transaction = new Transaction();
        transaction.setAmount(1.5);
        transaction.setDetails("details");
        transaction.setTimestamp(timestamp);
        transaction.setAccount(account);
        return transaction;
    }
}
