package neo.uap.domain.system;

import lombok.Data;
import neo.uap.domain.BaseDomain;

@Data
public class EmailTemplate extends BaseDomain {
    private String name;
    private String subject;
    private String content;
}
