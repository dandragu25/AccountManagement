package ro.dan.dragu.account.management.service.exception;

public class AccountNotFoundException extends AccountManagementException{

    public AccountNotFoundException(String message, Object... params) {
        super(message, params);
    }

    public AccountNotFoundException(String message) {
        super(message);


    }
}
