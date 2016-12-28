package uap.bo;

import uap.domain.User;

import java.util.List;

public interface UserBo {
    User getUser(String username);

    void addUser(User user);

    List<User> findAllUsers();
}
