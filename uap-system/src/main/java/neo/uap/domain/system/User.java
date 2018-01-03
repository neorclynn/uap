package neo.uap.domain.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import neo.uap.domain.common.Base;

@Data
public class User extends Base {
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String passwordToken;
    private String firstName;
    private String lastName;
    private String email;
    private Integer enabled;
    private String passwordExpiryDate;
    private String organizationType;
    private Long organizationCorrelation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Role role;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Privilege privilege;
}
