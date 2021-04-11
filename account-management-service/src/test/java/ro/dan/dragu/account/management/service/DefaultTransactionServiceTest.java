package ro.dan.dragu.account.management.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.dan.dragu.account.management.dal.entity.Account;
import ro.dan.dragu.account.management.dal.entity.Transaction;
import ro.dan.dragu.account.management.dal.repository.AccountRepository;
import ro.dan.dragu.account.management.dal.repository.TransactionRepository;
import ro.dan.dragu.account.management.service.mapper.TransactionDataMapper;
import ro.dan.dragu.account.management.service.model.TransactionData;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DefaultTransactionServiceTest {

    @Mock
    private  TransactionDataMapper transactionDataMapper;

    @Mock
    private  TransactionRepository transactionRepository;

    @Mock
    private  AccountRepository accountRepository;

    @InjectMocks
    private DefaultTransactionService defaultTransactionService;

    @Captor
    private ArgumentCaptor<LocalDateTime> localDateTimeArgumentCaptor;

    @Test
    public void testGetForLastTimeFrame() {
        Account account = new Account();
        when(accountRepository.findByName(eq("testUser"))).thenReturn(account);

        List<Transaction> transactions = new ArrayList<>();
        when(transactionRepository.findByAccountAndTimestampAfter(same(account), any(LocalDateTime.class))).thenReturn(transactions);

        List<TransactionData> transactionDataList = new ArrayList<>();
        when(transactionDataMapper.mapListTo(same(transactions))).thenReturn(transactionDataList);

        defaultTransactionService.getForLastTimeFrame("testUser", 1, ChronoUnit.DAYS);


        verify(accountRepository, times(1)).findByName(eq("testUser"));
        verify(transactionRepository, times(1)).findByAccountAndTimestampAfter(same(account), localDateTimeArgumentCaptor.capture());
        assertEquals(LocalDateTime.now().minusDays(1).getDayOfMonth(), localDateTimeArgumentCaptor.getValue().getDayOfMonth());
        verify(transactionDataMapper, times(1)).mapListTo(same(transactions));

    }
}
