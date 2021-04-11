package ro.dan.dragu.account.management.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.dan.dragu.account.management.service.UserService;
import ro.dan.dragu.account.management.web.mapper.AccountDetailsMapper;
import ro.dan.dragu.account.management.web.model.AccountDetails;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final AccountDetailsMapper accountDetailsMapper;

    @Autowired
    public AccountUserDetailsService(UserService userService, AccountDetailsMapper accountDetailsMapper) {
        this.userService = userService;
        this.accountDetailsMapper = accountDetailsMapper;
    }

    @Override
    public AccountDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return accountDetailsMapper.mapTo(userService.get(s));
    }
}
