package neo.uap.mapper;

import neo.uap.domain.system.Password;
import neo.uap.domain.system.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> list(@Param(value = "condition") User condition);

    User getByUsername(@Param(value = "username") String username);

    int edit(@Param(value = "user") User user);

    int changePassword(@Param(value = "password") Password password);

    int changePasswordToken(@Param(value = "password") Password password);
}
