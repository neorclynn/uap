package uap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.repository.config.EnableLdapRepositories;

@Configuration
@EnableLdapRepositories("com.cazysystems.appstore.model")
public class LdapConfig {
    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://localhost:10389");
        contextSource.setBase("o=uap");
        contextSource.setUserDn("uid=admin,ou=system");
        contextSource.setPassword("secret");
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }
}
