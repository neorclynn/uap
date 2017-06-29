package neo.uap.domain.system;

import lombok.Data;
import neo.uap.domain.BaseDomain;

@Data
public class Role extends BaseDomain {
    private String name;
}
