package uap.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import java.util.List;

@Service("UserBo")
public class UserBoImpl implements UserBo {
    @Autowired
    LdapTemplate ldapTemplate;

    final private Logger logger = LoggerFactory.getLogger(UserBoImpl.class);

    @Autowired
    private UserDao userDao;

    public User getUser(String username) {
        logger.error("AAA");

        return userDao.findByUsername(username);
    }

    public List<User> findAllUsers() {
        logger.error("AAA");
        return ldapTemplate.search(LdapQueryBuilder.query().where("objectclass").is("person"),
                new UserAttributesMapper());
    }

    public void addUser(User user) {
        Attributes attributes = new BasicAttributes();
        attributes.put("cn", "Neo1");
        attributes.put("sn", "Lynn1");

        BasicAttribute objectClassAttribute = new BasicAttribute("objectclass");
        objectClassAttribute.add("top");
        objectClassAttribute.add("person");
        objectClassAttribute.add("organizationalPerson");
        objectClassAttribute.add("inetOrgPerson");
        attributes.put(objectClassAttribute);
        ldapTemplate.bind("uid=" + user.getUsername() + ",ou=users", null, attributes);
    }

    private class UserAttributesMapper implements AttributesMapper<User> {
        public User mapFromAttributes(Attributes attributes) throws NamingException {
            User user = new User();
            user.setUsername((String) attributes.get("uid").get());
            return user;
        }
    }
}
