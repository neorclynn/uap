package uap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Import(LdapConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private LdapContextSource contextSource;

    public SecurityConfig(LdapContextSource contextSource) {
        this.contextSource = contextSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(contextSource.getBaseLdapPathAsString());
        auth.ldapAuthentication()
                .userSearchBase("ou=users").userSearchFilter("(uid={0})")
                .passwordCompare().passwordEncoder(new LdapShaPasswordEncoder());
    }
}
