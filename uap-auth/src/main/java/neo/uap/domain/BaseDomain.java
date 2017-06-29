package neo.uap.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseDomain {
    protected Long id;
    protected String createdBy;
    protected Date createdOn;
    protected String updatedBy;
    protected Date updatedOn;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Pageable pageable;
}
