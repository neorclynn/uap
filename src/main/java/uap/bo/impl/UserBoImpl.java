package uap.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uap.bo.UserBo;
import uap.domain.User;
import uap.persistence.UserDao;

@Service("UserBo")
public class UserBoImpl implements UserBo {
    @Autowired
    private UserDao userDao;

    public User getUser(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }
}
