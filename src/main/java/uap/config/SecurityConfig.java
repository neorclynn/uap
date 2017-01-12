package uap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final LdapContextSource contextSource;

    @Autowired
    public SecurityConfig(LdapContextSource contextSource) {
        this.contextSource = contextSource;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userDnPatterns("uid={0},ou=users," + contextSource.getBaseLdapPathAsString())
                .groupSearchBase("ou=groups," + contextSource.getBaseLdapPathAsString())
                .passwordCompare()
                .passwordAttribute("userPassword")
                .passwordEncoder(new LdapShaPasswordEncoder())
                .and()
                .contextSource()
                .url(contextSource.getUrls()[0])
                .root(contextSource.getBaseLdapPathAsString())
                .managerDn(contextSource.getUserDn())
                .managerPassword(contextSource.getPassword());
    }
}
