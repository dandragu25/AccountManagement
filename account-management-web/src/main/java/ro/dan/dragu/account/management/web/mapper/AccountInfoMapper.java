package ro.dan.dragu.account.management.web.mapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ro.dan.dragu.account.management.service.mapper.ObjectMapper;
import ro.dan.dragu.account.management.service.model.User;
import ro.dan.dragu.account.management.web.model.AccountDetails;
import ro.dan.dragu.account.management.web.model.AccountInfo;

import java.util.List;


@Service
public class AccountInfoMapper extends ObjectMapper<User, AccountInfo> {

    @Override
    public User mapFrom(AccountInfo source) {
       throw new  UnsupportedOperationException();
    }

    @Override
    public AccountInfo mapTo(User source) {
        AccountInfo result = new AccountInfo();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setFullName(source.getFullName());
        result.setEmail(source.getEmail());
        result.setDisabled(source.getDisabled());
        result.setRole(source.getRole());
        return result;
    }





}
