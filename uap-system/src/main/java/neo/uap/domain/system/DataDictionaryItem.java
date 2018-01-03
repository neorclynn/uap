package neo.uap.domain.system;

import lombok.Data;
import neo.uap.domain.common.Base;
import neo.uap.domain.common.Modifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_data_dictionary_item")
public class DataDictionaryItem extends Base {
    @Column(name = "value")
    private String value;

    @Column(name = "text")
    @Modifiable
    private String text;

    @Column(name = "extension1")
    @Modifiable
    private String extension1;

    @Column(name = "extension2")
    @Modifiable
    private String extension2;

    @Column(name = "extension3")
    @Modifiable
    private String extension3;

    @Column(name = "extension4")
    @Modifiable
    private String extension4;

    @Column(name = "type_id")
    private Long typeId;
}
