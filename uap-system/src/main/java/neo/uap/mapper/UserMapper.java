package neo.uap.mapper;

import neo.uap.domain.system.Password;
import neo.uap.domain.system.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> list(@Param(value = "condition") User condition);

    @Cacheable("users")
    User getByUsername(@Param(value = "username") String username);

    @CacheEvict(value = "users", allEntries = true)
    int edit(@Param(value = "user") User user);

    @CacheEvict(value = "users", allEntries = true)
    int changePassword(@Param(value = "password") Password password);

    @CacheEvict(value = "users", allEntries = true)
    int changePasswordToken(@Param(value = "password") Password password);
}
