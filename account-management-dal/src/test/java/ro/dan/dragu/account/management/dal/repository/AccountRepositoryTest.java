package ro.dan.dragu.account.management.dal.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ro.dan.dragu.account.management.dal.entity.Account;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {JpaTestConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@DataJpaTest(showSql = false)
@Ignore
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void testSave() {
        Account account =TestUtils.createAccount(1);

        accountRepository.save(account);

        assertEquals(1, accountRepository.findAll()
                .size());
    }

    @Test
    public void testSaveEmailIsNull() {
        Account account = TestUtils.createAccount(1);
        account.setEmail(null);

        DataIntegrityViolationException e = assertThrows(DataIntegrityViolationException.class, () ->   accountRepository.save(account));
        assertTrue( e.getMessage().contains("not-null property references a null or transient value : ro.dan.dragu.account.management.dal.entity.Account.email"));

    }

    @Test
    public void testSaveUserNameIsDuplicated() {
        for (int i = 1; i <= 10; i++) {
            Account account = TestUtils.createAccount(i);
            accountRepository.save(account);
        }
        Account account = TestUtils.createAccount(11);
        account.setName("account7");
        accountRepository.save(account);


        DataIntegrityViolationException e = assertThrows(DataIntegrityViolationException.class, () ->   accountRepository.save(account));
        assertTrue( e.getMessage().contains("constraint [\"PUBLIC.UK_6U7V06WRRQEPPB5K5ABL0KQVA_INDEX_A ON PUBLIC.ACCOUNTS(USER_NAME)"));

    }

    @Test
    public void testFindByName() {
        for (int i = 1; i <= 100; i++) {
            Account account = TestUtils.createAccount(i);
            accountRepository.save(account);
        }

        Account account = accountRepository.findByName("account69");
        assertEquals("account69", account.getName());
    }

    @Test
    public void testFindByNameNoResults() {
        for (int i = 1; i <= 10; i++) {
            Account account = TestUtils.createAccount(i);
            accountRepository.save(account);
        }

        assertNull(accountRepository.findByName("account69"));
    }
}
