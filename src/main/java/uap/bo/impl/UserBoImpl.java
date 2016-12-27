package uap.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import uap.bo.UserBo;
import uap.domain.User;
import uap.dao.UserDao;

import java.util.List;

@Service("UserBo")
public class UserBoImpl implements UserBo {
    private UserDao userDao;

    @Autowired
    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public List<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
