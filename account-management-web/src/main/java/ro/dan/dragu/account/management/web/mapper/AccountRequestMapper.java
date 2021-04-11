package ro.dan.dragu.account.management.web.mapper;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.dan.dragu.account.management.dal.entity.AccountRole;
import ro.dan.dragu.account.management.service.mapper.ObjectMapper;
import ro.dan.dragu.account.management.service.model.User;
import ro.dan.dragu.account.management.web.exception.ValidationException;
import ro.dan.dragu.account.management.web.model.AccountInfo;
import ro.dan.dragu.account.management.web.model.AccountRequest;


@Service
public class AccountRequestMapper extends ObjectMapper<AccountRequest, User> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountRequestMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User mapTo(AccountRequest source) {
        EmailValidator emailValidator = EmailValidator.getInstance(true);
        if ( !emailValidator.isValid(source.getEmail())){
            throw new ValidationException("Invalid email: %s", source.getEmail());
        }
        User user = new User();
        user.setName(source.getName());
        user.setFullName(source.getFullName());
        user.setEmail(source.getEmail());
        user.setPassword(passwordEncoder.encode(source.getPassword()));
        user.setRole(AccountRole.ROLE_USER);
        user.setDisabled(false);
        return user;
    }

    @Override
    public AccountRequest mapFrom(User source) {
        throw new UnsupportedOperationException();
    }
}
