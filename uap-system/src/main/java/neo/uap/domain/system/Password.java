package neo.uap.domain.system;

import lombok.Data;

@Data
public class Password {
    private String username;
    private String token;
    private String password;
    private String newPassword;
    private String newPasswordEncoded;
}
