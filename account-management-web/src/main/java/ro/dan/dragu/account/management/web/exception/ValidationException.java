package ro.dan.dragu.account.management.web.exception;

import ro.dan.dragu.account.management.service.exception.AccountManagementException;

public class ValidationException extends AccountManagementException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Object... params) {
        super(message, params);
    }
}
