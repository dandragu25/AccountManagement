package ro.dan.dragu.account.management.web.rest.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.dan.dragu.account.management.service.TransactionService;
import ro.dan.dragu.account.management.service.UserService;
import ro.dan.dragu.account.management.service.model.TransactionData;
import ro.dan.dragu.account.management.web.exception.ValidationException;
import ro.dan.dragu.account.management.web.mapper.AccountRequestMapper;
import ro.dan.dragu.account.management.web.model.AccountRequest;
import ro.dan.dragu.account.management.web.model.TimeUnit;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/account/management/user")
public class UserController {

    private final UserService userService;
    private final TransactionService transactionService;
    private final AccountRequestMapper accountRequestMapper;

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService, TransactionService transactionService, AccountRequestMapper accountRequestMapper) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.accountRequestMapper = accountRequestMapper;
    }

    @PostMapping(value = "/create")
    public void createAccount(@RequestBody final AccountRequest accountRequest) {
        logger.info("Creating account for user: {}", accountRequest.getName());
        userService.create(accountRequestMapper.mapTo(accountRequest));
        logger.info("Account created for user: {}", accountRequest.getName());
    }

    @DeleteMapping(value = "/delete")
    public void deleteAccount(Principal principal) {
        logger.warn("Deleting account for user: {}", principal.getName());
        userService.disable(principal.getName());
        logger.info("Account deleted for user: {}", principal.getName());
    }

    @GetMapping(value = "/listTransactions")
    public List<TransactionData> listTransactions(Principal principal, @RequestParam Integer timeFrame, @RequestParam String timeUnit) {
        logger.info("Listing transactions for user: {} for last {} {}", principal.getName(), timeFrame, timeUnit);
        return transactionService.getForLastTimeFrame(principal.getName(), timeFrame, validateTimeUnit(timeUnit).getChronoUnit());
    }

    private TimeUnit validateTimeUnit(String timeUnit) {
        try {
            return TimeUnit.valueOf(timeUnit);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid value for time unit: %s. Accepted values: %s" , timeUnit, Arrays.toString(TimeUnit.values()));
        }

    }

}