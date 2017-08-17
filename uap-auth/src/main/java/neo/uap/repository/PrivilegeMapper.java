package neo.uap.repository;

import neo.uap.domain.system.Privilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PrivilegeMapper {
    List<Privilege> listForUser(@Param(value = "username") String username);
}
