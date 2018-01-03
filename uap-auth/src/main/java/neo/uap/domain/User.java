package neo.uap.domain;

import lombok.Data;
import neo.uap.domain.common.Base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_user")
public class User extends Base {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_enabled")
    private Boolean enabled;

    @Column(name = "password_expiry_date")
    private String passwordExpiryDate;
}
