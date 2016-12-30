package uap.bo;

import uap.domain.sys.User;

import java.util.List;

public interface UserBo {
    User getUser(String username);

    User addUser(User user);

    List<User> findAllUsers();
}
