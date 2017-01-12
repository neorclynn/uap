package uap.bo;

import uap.domain.sys.User;

import java.util.List;

public interface UserBo {
    List<User> findAllUsers();

    User findByUsername(String username);

    void addUser(User user);
}
