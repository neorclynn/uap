package uap.bo;

import uap.domain.User;

import java.util.List;

public interface UserBo {
    User getUser(String username);

    List<User> findAllUsers();
}
