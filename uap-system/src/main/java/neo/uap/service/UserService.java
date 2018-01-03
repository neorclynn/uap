package neo.uap.service;

import neo.uap.domain.system.Privilege;
import neo.uap.domain.system.User;

import java.util.List;

public interface UserService {
    User getByUsername(String username);

    List<Privilege> listNonAuthRequiredPrivileges();
}
