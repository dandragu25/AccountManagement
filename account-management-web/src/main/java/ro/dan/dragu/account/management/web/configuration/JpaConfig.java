package ro.dan.dragu.account.management.web.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@Profile("production")
@EnableJpaRepositories("ro.dan.dragu.account.management.dal")
@EnableTransactionManagement
@EntityScan("ro.dan.dragu.account.management.dal.entity")
public class JpaConfig {
}
