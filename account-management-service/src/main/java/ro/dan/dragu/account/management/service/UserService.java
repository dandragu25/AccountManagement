package ro.dan.dragu.account.management.service;

import ro.dan.dragu.account.management.service.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User get(String userName);

    void create(User user);

    void disable(String userName);
}
