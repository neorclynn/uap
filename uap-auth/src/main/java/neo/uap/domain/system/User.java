package neo.uap.domain.system;

import lombok.Data;

@Data
public final class User {
    private String username;
    private String password;
    private Boolean enabled;
    private String passwordExpiryDate;
}
