package uap.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uap.bo.UserBo;
import uap.persistence.UserRepository;
import uap.domain.sys.User;

import java.util.List;

@Service("UserBo")
public class UserBoImpl implements UserBo {
    final private Logger logger = LoggerFactory.getLogger(UserBoImpl.class);

    @Autowired
    private UserRepository userRepository;

    public User getUser(String username) {
        logger.error("AAA");

        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        logger.error("AAA");
        return userRepository.findAll();

        //return ldapTemplate.search(LdapQueryBuilder.query().where("objectclass").is("person"),
                //new UserAttributesMapper());
    }



    public User addUser(User user) {
        return userRepository.save(user);
//        Attributes attributes = new BasicAttributes();
//        attributes.put("cn", "Neo1");
//        attributes.put("sn", "Lynn1");
//
//        BasicAttribute objectClassAttribute = new BasicAttribute("objectclass");
//        objectClassAttribute.add("top");
//        objectClassAttribute.add("person");
//        objectClassAttribute.add("organizationalPerson");
//        objectClassAttribute.add("inetOrgPerson");
//        attributes.put(objectClassAttribute);
        //ldapTemplate.bind("uid=" + user.getUsername() + ",ou=users", null, attributes);
    }

//    private class UserAttributesMapper implements AttributesMapper<User> {
//        public User mapFromAttributes(Attributes attributes) throws NamingException {
//            User user = new User();
//            user.setUsername((String) attributes.get("uid").get());
//            return user;
//        }
//    }
}
