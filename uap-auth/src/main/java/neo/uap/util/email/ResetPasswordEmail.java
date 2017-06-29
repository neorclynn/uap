package neo.uap.util.email;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ResetPasswordEmail extends BaseEmail {
    public static final String NAME = "{name}";
    public static final String TEMP_PASSWORD = "{tempPassword}";

    @PostConstruct
    public void init() {
        name = "Reset Password";
    }
}
