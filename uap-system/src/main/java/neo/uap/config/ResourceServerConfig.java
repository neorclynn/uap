package neo.uap.config;

import neo.uap.domain.system.Privilege;
import neo.uap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableAutoConfiguration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Value("${services.auth.host}")
    private String server;

    @Value("${services.auth.port}")
    private String port;

    @Value("${services.auth.endpoints.check-token}")
    private String checkTokenEndpoint;

    @Value("${services.auth.client-id}")
    private String clientId;

    @Value("${services.auth.secret}")
    private String secret;

    @Autowired
    private UserService userService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        for (Privilege p : userService.listNonAuthRequiredPrivileges()) {
            http.authorizeRequests().antMatchers(HttpMethod.valueOf(p.getMethod()), p.getPath()).permitAll();
        }
        http.authorizeRequests().anyRequest().authenticated();
        http.csrf().disable();
    }

    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(server + ":" + port + "/" + checkTokenEndpoint);
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(secret);
        return tokenService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}