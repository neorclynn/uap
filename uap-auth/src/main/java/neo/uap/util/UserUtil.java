package neo.uap.util;

import neo.uap.domain.system.Privilege;
import neo.uap.service.UserService;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserUtil {
    private static UserService userService;

    @Autowired
    private UserService userServiceNonStatic;

    private static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentUsername() {
        return getCurrentAuthentication().getName();
    }

    public static boolean hasAuthority(String method, String path) {
        if (isNonAuthRequiredPrivilege(method, path)) {
            return true;
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(method + " " + path);
        return getCurrentAuthentication().getAuthorities().contains(authority);
    }

    private static boolean isNonAuthRequiredPrivilege(String method, String path) {
        for (Privilege p : userService.listNonAuthRequiredPrivileges()) {
            if (StringUtils.equals(p.getMethod(), method) && StringUtils.equals(p.getPath(), path)) {
                return true;
            }
        }
        return false;
    }

    @PostConstruct
    public void init() {
        userService = userServiceNonStatic;
    }
}
