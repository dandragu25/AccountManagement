package ro.dan.dragu.account.management.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import ro.dan.dragu.account.management.dal.entity.Account;
import ro.dan.dragu.account.management.dal.repository.AccountRepository;
import ro.dan.dragu.account.management.dal.repository.TransactionRepository;
import ro.dan.dragu.account.management.service.exception.AccountNotFoundException;
import ro.dan.dragu.account.management.service.mapper.UserMapper;
import ro.dan.dragu.account.management.service.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DefaultUserServiceTest {

    @Mock
    private AccountRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private DefaultUserService defaultUserService;

    @Test
    public void testGetAll() {
        List<Account> accounts = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(accounts);

        List<User> users = new ArrayList<>();
        when(userMapper.mapListTo(same(accounts))).thenReturn(users);

        List<User> result = defaultUserService.getAll();

        assertSame(result, users);
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).mapListTo(same(accounts));

    }

    @Test
    public void testGet() {
        Account account = new Account();
        when(userRepository.findByName(eq("testUser"))).thenReturn(account);

        User user = new User();
        when(userMapper.mapTo(same(account))).thenReturn(user);

        User result =  defaultUserService.get("testUser");
        assertSame(result, user);

        verify(userRepository, times(1)).findByName(eq("testUser"));
        verify(userMapper, times(1)).mapTo(same(account));
    }

    @Test
    public void testGetAccountNotFound() {
        when(userRepository.findByName(eq("testUser"))).thenReturn(null);

        AccountNotFoundException e = assertThrows(AccountNotFoundException.class, () ->  defaultUserService.get("testUser"));
        assertEquals("No account was found for serName: testUser", e.getMessage());

        verify(userRepository, times(1)).findByName(eq("testUser"));
        verify(userMapper, times(0)).mapTo(any());
    }


    @Test
    public void testCreate() {
        User user = new User();
        Account account = new Account();
        when(userMapper.mapFrom(same(user))).thenReturn(account);

        ReflectionTestUtils.setField(defaultUserService, "nrOfTransactions", 10);

        defaultUserService.create(user);

        verify(userRepository, times(1)).save(same(account));
        verify(userMapper, times(1)).mapFrom(same(user));

    }

    @Test
    public void testDisable() {
        Account account = new Account();
        account.setDisabled(false);
        when(userRepository.findByName(eq("testUser"))).thenReturn(account);

        defaultUserService.disable("testUser");

        verify(userRepository, times(1)).findByName(eq("testUser"));
        assertTrue(account.getDisabled());
    }


    @Test
    public void testDisableAccountNotFound() {
        when(userRepository.findByName(eq("testUser"))).thenReturn(null);

        AccountNotFoundException e = assertThrows(AccountNotFoundException.class, () ->  defaultUserService.disable("testUser"));
        assertEquals("No account was found for serName: testUser", e.getMessage());

        verify(userRepository, times(1)).findByName(eq("testUser"));
    }
}
