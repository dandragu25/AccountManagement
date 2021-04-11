package ro.dan.dragu.account.management.web.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dan.dragu.account.management.service.UserService;
import ro.dan.dragu.account.management.web.mapper.AccountInfoMapper;
import ro.dan.dragu.account.management.web.model.AccountInfo;

import java.util.List;

@RestController
@RequestMapping("/account/management/admin")
public class AdminController {

    private final AccountInfoMapper accountInfoMapper;
    private final UserService userService;

    @Autowired
    public AdminController(AccountInfoMapper accountInfoMapper, UserService userService) {
        this.accountInfoMapper = accountInfoMapper;
        this.userService = userService;
    }

    @GetMapping(value = "/listAll")
    public List<AccountInfo> listAccounts() {
        return accountInfoMapper.mapListTo(userService.getAll());
    }

}
