package neo.uap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.LdapTemplate;
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
    private CustomizedUserDetailsService userDetailsService;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Value("${spring.ldap.user-dn-pattern}")
    private String ldapUserDnPattern;

    @Value("${spring.ldap.group-search-base}")
    private String ldapGroupSearchBase;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        LdapContextSource ldapContextSource = (LdapContextSource) ldapTemplate.getContextSource();

        auth.ldapAuthentication()
                .userDnPatterns(ldapUserDnPattern)
                .groupSearchBase(ldapGroupSearchBase)
                .passwordCompare()
                .passwordAttribute("userPassword")
                .and()
                .passwordEncoder(passwordEncoder())
                .contextSource()
                .url(ldapContextSource.getUrls()[0])
                .root(ldapContextSource.getBaseLdapPathAsString())
                .managerDn(ldapContextSource.getUserDn())
                .managerPassword(ldapContextSource.getPassword());

        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
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
