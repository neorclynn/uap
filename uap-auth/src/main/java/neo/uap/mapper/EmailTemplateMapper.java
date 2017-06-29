package neo.uap.mapper;

import neo.uap.domain.system.EmailTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmailTemplateMapper {
    EmailTemplate get(@Param(value = "name") String name);
}
