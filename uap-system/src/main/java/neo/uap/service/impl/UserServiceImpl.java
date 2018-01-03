package neo.uap.service.impl;

import neo.uap.domain.system.Privilege;
import neo.uap.domain.system.User;
import neo.uap.mapper.PrivilegeMapper;
import neo.uap.mapper.UserMapper;
import neo.uap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Cacheable("users")
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Cacheable("nonAuthRequiredPrivileges")
    public List<Privilege> listNonAuthRequiredPrivileges() {
        Privilege condition = new Privilege();
        condition.setAuthRequired(false);
        return privilegeMapper.list(condition);
    }
}
