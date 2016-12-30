package uap.domain.sys;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_data_dictionary_type")
public class DataDictionaryType implements Serializable {
    @Id
    @GeneratedValue
    int id;

    @Column
    String name;

    @Column
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
