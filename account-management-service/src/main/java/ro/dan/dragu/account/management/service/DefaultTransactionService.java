package ro.dan.dragu.account.management.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.dan.dragu.account.management.dal.entity.Account;
import ro.dan.dragu.account.management.dal.repository.AccountRepository;
import ro.dan.dragu.account.management.dal.repository.TransactionRepository;
import ro.dan.dragu.account.management.service.mapper.TransactionDataMapper;
import ro.dan.dragu.account.management.service.model.TransactionData;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DefaultTransactionService implements TransactionService {

    private final TransactionDataMapper transactionDataMapper;

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private Logger logger = LoggerFactory.getLogger(DefaultTransactionService.class);

    @Autowired
    public DefaultTransactionService(TransactionDataMapper transactionDataMapper, TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionDataMapper = transactionDataMapper;
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<TransactionData> getForLastTimeFrame(String userName,  Integer timeFrame, ChronoUnit timeUnit) {
        logger.debug("-> getForLastTimeFrame() -> userName:{}, timeFrame: {}, timeUnit: {}" + userName, timeFrame, timeUnit );
        Account account = accountRepository.findByName(userName);
        return transactionDataMapper.mapListTo(transactionRepository.findByAccountAndTimestampAfter(account, LocalDateTime.now()
                .minus(timeFrame, timeUnit)));
    }

}

