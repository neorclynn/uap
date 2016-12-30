package uap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uap.bo.UserBo;
import uap.domain.sys.User;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserService {
    @Autowired
    private UserBo userBo;

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<List<User>>(userBo.findAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        return new ResponseEntity<User>(userBo.getUser(username), HttpStatus.OK);
    }
}
