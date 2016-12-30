package uap.domain.sys;

import uap.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Role extends BaseDomain {
    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
