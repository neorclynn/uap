package neo.uap;

import neo.uap.domain.system.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapTest {
    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test() {
        User user = ldapTemplate.findOne(LdapQueryBuilder.query().where("cn").is("a"), User.class);
        user.setPassword(passwordEncoder.encode("a"));
        ldapTemplate.update(user);

        //System.out.println(passwordEncoder.matches("1234", user.getPassword()));
    }
}
