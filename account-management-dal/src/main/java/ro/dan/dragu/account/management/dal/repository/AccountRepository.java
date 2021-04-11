package ro.dan.dragu.account.management.dal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dan.dragu.account.management.dal.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByName(String name);

}
