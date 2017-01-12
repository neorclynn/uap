package uap.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;
import uap.bo.UserBo;
import uap.domain.sys.User;

import java.util.List;

@Service("UserBo")
public class UserBoImpl implements UserBo {
    private final LdapTemplate ldapTemplate;

    final private Logger logger = LoggerFactory.getLogger(UserBoImpl.class);

    public UserBoImpl(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public List<User> findAllUsers() {
        return ldapTemplate.findAll(User.class);
    }

    public User findByUsername(String username) {
        return ldapTemplate.findOne(LdapQueryBuilder.query().where("uid").is(username), User.class);
    }

    public void addUser(User user) {
        ldapTemplate.create(user);
    }

//    private class UserAttributesMapper implements AttributesMapper<User> {
//        public User mapFromAttributes(Attributes attributes) throws NamingException {
//            User user = new User();
//            user.setUsername((String) attributes.get("uid").get());
//            return user;
//        }
//    }
}
