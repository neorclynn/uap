package uap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uap.domain.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);

    List<User> findAll();
}
