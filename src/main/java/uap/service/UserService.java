package uap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uap.bo.UserBo;
import uap.domain.User;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserService {
    private UserBo userBo;

    @Autowired
    @Required
    public void setUserBo(UserBo userBo) {
        this.userBo = userBo;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<List<User>>(userBo.findAll(), HttpStatus.OK);
    }
}
