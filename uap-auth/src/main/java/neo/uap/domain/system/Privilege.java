package neo.uap.domain.system;

import lombok.Data;

@Data
public class Privilege {
    private String method;
    private String path;
    private Boolean authRequired;
}
