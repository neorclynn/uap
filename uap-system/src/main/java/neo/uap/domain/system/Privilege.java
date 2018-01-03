package neo.uap.domain.system;

import lombok.Data;
import neo.uap.domain.common.Base;

@Data
public class Privilege extends Base {
    private String method;
    private String path;
    private Boolean authRequired;
}
