package neo.uap.domain.system;

import lombok.Data;
import neo.uap.domain.BaseDomain;

@Data
public class Privilege extends BaseDomain {
    private String method;
    private String path;
    private Boolean authRequired;
}
