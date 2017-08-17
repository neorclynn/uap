package neo.uap.repository;

import neo.uap.domain.system.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User getByUsername(@Param(value = "username") String username);
}
