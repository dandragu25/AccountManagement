package ro.dan.dragu.account.management.dal.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories("ro.dan.dragu.account.management.dal")
@EntityScan("ro.dan.dragu.account.management.dal.entity")
public class JpaTestConfiguration {
}
