package uap.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uap.domain.sys.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAll();

    User save(User user);
}
