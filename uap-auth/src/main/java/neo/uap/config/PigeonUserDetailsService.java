package neo.uap.config;

import neo.uap.domain.system.Privilege;
import neo.uap.domain.system.User;
import neo.uap.mapper.CommonMapper;
import neo.uap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PigeonUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private CommonMapper commonMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        boolean enabled = user.getEnabled() > 0;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = user.getPasswordExpiryDate() == null ? true :
                user.getPasswordExpiryDate().compareTo(commonMapper.currentDate()) >= 0;
        boolean accountNonLocked = true;

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<Privilege> privileges = userService.listPrivilegesForUser(username);
        for (Privilege p : privileges) {
            authorities.add(new SimpleGrantedAuthority(p.getMethod() + " " + p.getPath()));
        }

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
