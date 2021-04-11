package ro.dan.dragu.account.management.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.dan.dragu.account.management.dal.entity.Account;
import ro.dan.dragu.account.management.dal.entity.Transaction;
import ro.dan.dragu.account.management.dal.repository.AccountRepository;
import ro.dan.dragu.account.management.dal.repository.TransactionRepository;
import ro.dan.dragu.account.management.service.exception.AccountNotFoundException;
import ro.dan.dragu.account.management.service.mapper.UserMapper;
import ro.dan.dragu.account.management.service.model.User;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DefaultUserService implements UserService {


    private final AccountRepository userRepository;

    private final TransactionRepository transactionRepository;

    private final UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(DefaultUserService.class);

    @Value("${transactions.per.account}")
    private int nrOfTransactions;

    @Autowired
    public DefaultUserService(AccountRepository userRepository, TransactionRepository transactionRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAll() {
        return userMapper.mapListTo(userRepository.findAll());
    }

    @Override
    public User get(String userName) {
        logger.debug("-> get() -> userName:{}" + userName);
        return userMapper.mapTo(getAccount(userName));
    }

    @Override
    public void create(User user) {
        logger.debug("-> create() -> userName:{}, fullName: {}, email: {}, role: {}" + user.getName(), user.getFullName(), user.getEmail(), user.getRole());
        Account account = userRepository.save(userMapper.mapFrom(user));
        addTransactions(account);
    }

    @Override
    @Transactional
    public void disable(String userName) {
        Account account = getAccount(userName);
        account.setDisabled(true);
    }

    private void addTransactions(Account account){
        List<Transaction> transactions = new ArrayList<>();
        for (int i =1; i<=nrOfTransactions;i++){
            transactions.add(generateTransaction(account,i));
        }
        transactionRepository.saveAll(transactions);
    }

    private Transaction generateTransaction( Account account, int index){
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount( new Random().nextInt(100) / 100.0);
        transaction.setDetails("Details");
        transaction.setTimestamp(LocalDateTime.now().minusHours(index));
        return transaction;

    };

    private Account getAccount(String userName) {
        Account account = userRepository.findByName(userName);
        if (account == null) {
            throw new AccountNotFoundException("No account was found for serName: %s", userName);
        }
        return account;
    }
}
