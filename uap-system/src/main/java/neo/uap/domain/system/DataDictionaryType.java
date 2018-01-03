package neo.uap.domain.system;

import lombok.Data;
import neo.uap.domain.common.Base;
import neo.uap.domain.common.Modifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_data_dictionary_type")
public class DataDictionaryType extends Base {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Modifiable
    private String description;

    @Column(name = "parent_type_id")
    private Long parentTypeId;
}
