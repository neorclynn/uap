package neo.uap.service.impl;

import neo.uap.domain.system.Password;
import neo.uap.domain.system.Privilege;
import neo.uap.domain.system.User;
import neo.uap.exception.WrongPasswordException;
import neo.uap.exception.WrongPasswordTokenException;
import neo.uap.mapper.PrivilegeMapper;
import neo.uap.mapper.UserMapper;
import neo.uap.service.UserService;
import neo.uap.util.Preconditions;
import neo.uap.util.email.ResetPasswordEmail;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Autowired
    private ResetPasswordEmail resetPasswordEmail;

    public List<User> list(User condition) {
        return userMapper.list(condition);
    }

    @Cacheable("users")
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @CacheEvict(value = "users", key = "#user.username")
    public int edit(User user) {
        return userMapper.edit(user);
    }

    public List<Privilege> listPrivileges(Privilege condition) {
        return privilegeMapper.list(condition);
    }

    @Cacheable("nonAuthRequiredPrivileges")
    public List<Privilege> listNonAuthRequiredPrivileges() {
        Privilege condition = new Privilege();
        condition.setAuthRequired(false);
        return listPrivileges(condition);
    }

    @Cacheable(value = "userPrivileges", key = "#username")
    public List<Privilege> listPrivilegesForUser(String username) {
        return privilegeMapper.listForUser(username);
    }

    @CacheEvict(value = "users", key = "#username")
    public int resetPassword(String username) {
        User userExisted = getByUsername(username);
        Preconditions.checkResourceNotFound(userExisted);

        String token = RandomStringUtils.randomAlphanumeric(16);
        Password password = new Password();
        password.setUsername(username);
        password.setToken(token);
        sendEmail(userExisted, token);
        return userMapper.changePasswordToken(password);
    }

    @CacheEvict(value = "users", key = "#password.username")
    public int changePasswordWithToken(Password password) {
        User userExisted = getByUsername(password.getUsername());
        Preconditions.checkResourceNotFound(userExisted);
        Preconditions.checkPrerequisite(checkPasswordToken(password.getToken(), userExisted.getPasswordToken()),
                WrongPasswordTokenException.class);
        password.setNewPasswordEncoded(passwordEncoder.encode(password.getNewPassword()));
        return userMapper.changePassword(password);
    }

    @CacheEvict(value = "users", key = "#password.username")
    public int changePassword(Password password) {
        User userExisted = getByUsername(password.getUsername());
        Preconditions.checkResourceNotFound(userExisted);
        Preconditions.checkPrerequisite(checkPassword(password.getPassword(), userExisted.getPassword()),
                WrongPasswordException.class);
        password.setNewPasswordEncoded(passwordEncoder.encode(password.getNewPassword()));
        return userMapper.changePassword(password);
    }

    private boolean checkPasswordToken(String requiredPasswordToken, String ExistedPasswordToken) {
        return StringUtils.equals(requiredPasswordToken, ExistedPasswordToken);
    }

    private boolean checkPassword(String requiredPassword, String ExistedPassword) {
        return passwordEncoder.matches(requiredPassword, ExistedPassword);
    }

    private void sendEmail(User user, String tempPassword) {
        resetPasswordEmail.setTo(user.getEmail());
        resetPasswordEmail.addParameter(ResetPasswordEmail.NAME, user.getFirstName() + " " + user.getLastName());
        resetPasswordEmail.addParameter(ResetPasswordEmail.TEMP_PASSWORD, tempPassword);
        resetPasswordEmail.send();
    }
}
