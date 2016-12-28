package uap.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;
import uap.bo.UserBo;
import uap.domain.User;
import uap.persistence.UserDao;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

@Service("UserBo")
public class UserBoImpl implements UserBo {
    @Autowired
    LdapTemplate ldapTemplate;

    @Autowired
    private UserDao userDao;

    public User getUser(String username) {
        return userDao.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return ldapTemplate.search(LdapQueryBuilder.query().where("objectclass").is("person"),
                new UserAttributesMapper());
    }

    private class UserAttributesMapper implements AttributesMapper<User> {
        public User mapFromAttributes(Attributes attributes) throws NamingException {
            User user = new User();
            user.setUsername((String) attributes.get("uid").get());
            return user;
        }
    }
}
