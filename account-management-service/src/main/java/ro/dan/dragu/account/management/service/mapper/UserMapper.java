package ro.dan.dragu.account.management.service.mapper;

import org.springframework.stereotype.Service;
import ro.dan.dragu.account.management.dal.entity.Account;
import ro.dan.dragu.account.management.service.model.User;

@Service
public class UserMapper extends ObjectMapper<Account, User>{

    @Override
    public User mapTo(Account source) {
        User user = new User();
        user.setId(source.getId());
        user.setEmail(source.getEmail());
        user.setFullName(source.getFullName());
        user.setPassword(source.getPassword());
        user.setName(source.getName());
        user.setDisabled(source.getDisabled());
        user.setRole(source.getRole());
        return user;
    }

    @Override
    public Account mapFrom(User source) {
        Account account = new Account();
        account.setDisabled(false);
        account.setEmail(source.getEmail());
        account.setFullName(source.getFullName());
        account.setName(source.getName());
        account.setPassword(source.getPassword());
        account.setRole(source.getRole());
        return account;
    }
}
