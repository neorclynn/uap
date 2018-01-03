package neo.uap.config;

import neo.uap.domain.User;
import neo.uap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        boolean enabled = user.getEnabled();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = user.getPasswordExpiryDate() == null ? true :
                user.getPasswordExpiryDate().compareTo(userRepository.currentDate()) >= 0;
        boolean accountNonLocked = true;

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
