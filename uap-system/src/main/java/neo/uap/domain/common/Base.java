package neo.uap.domain.common;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class Base {
    @Id
    @GeneratedValue
    protected Long id;

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "created_on")
    protected Date createdOn;

    @Column(name = "updated_by")
    protected String updatedBy;

    @Column(name = "updated_on")
    protected Date updatedOn;
}
