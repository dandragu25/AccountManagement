package ro.dan.dragu.account.management.web.mapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ro.dan.dragu.account.management.service.mapper.ObjectMapper;
import ro.dan.dragu.account.management.service.model.User;
import ro.dan.dragu.account.management.web.model.AccountDetails;

import java.util.List;


@Service
public class AccountDetailsMapper extends ObjectMapper<User, AccountDetails> {

    @Override
    public User mapFrom(AccountDetails source) {
       throw new  UnsupportedOperationException();
    }

    @Override
    public AccountDetails mapTo(User source) {
        return new AccountDetails(source.getPassword(), source.getName(), !source.getDisabled(), mapAuthorities(source));
    }

    private List<GrantedAuthority> mapAuthorities(User user) {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }




}
