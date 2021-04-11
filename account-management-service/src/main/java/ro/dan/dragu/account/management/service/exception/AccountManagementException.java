package ro.dan.dragu.account.management.service.exception;

public class AccountManagementException extends RuntimeException{

    public AccountManagementException(String message) {
        super(message);
    }

    public AccountManagementException(String message, Object... params) {
        super(String.format(message, params));
    }
}
