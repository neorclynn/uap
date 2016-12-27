package uap.bo;

import uap.domain.User;

import java.util.List;

public interface UserBo {
    List<User> findAll();

    List<User> findByUsername(String username);
}
