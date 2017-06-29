package neo.uap.service;

import neo.uap.domain.system.Password;
import neo.uap.domain.system.Privilege;
import neo.uap.domain.system.User;

import java.util.List;

public interface UserService {
    List<User> list(User condition);

    User getByUsername(String username);

    int edit(User user);

    List<Privilege> listPrivileges(Privilege condition);

    List<Privilege> listNonAuthRequiredPrivileges();

    List<Privilege> listPrivilegesForUser(String username);

    int resetPassword(String username);

    int changePasswordWithToken(Password password);

    int changePassword(Password password);
}
