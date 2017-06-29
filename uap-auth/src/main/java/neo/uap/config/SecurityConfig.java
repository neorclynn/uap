package neo.uap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LdapContextSource ldapContextSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String ldapUrl = ldapContextSource.getUrls()[0];
        String ldapBase = ldapContextSource.getBaseLdapPathAsString();
        String ldapUserDn = ldapContextSource.getUserDn();
        String ldapPassword = ldapContextSource.getPassword();

        auth.ldapAuthentication()
                .userDnPatterns("uid={0},ou=users," + ldapBase)
                .groupSearchBase("ou=groups," + ldapBase)
                .passwordCompare()
                .passwordAttribute("userPassword")
                .and()
                .passwordEncoder(passwordEncoder())
                .contextSource()
                .url(ldapUrl)
                .root(ldapBase)
                .managerDn(ldapUserDn)
                .managerPassword(ldapPassword);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
