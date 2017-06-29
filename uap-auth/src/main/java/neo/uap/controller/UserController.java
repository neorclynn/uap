package neo.uap.controller;

import neo.uap.domain.ResponseBody;
import neo.uap.domain.system.Password;
import neo.uap.domain.system.User;
import neo.uap.service.UserService;
import neo.uap.util.Preconditions;
import neo.uap.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/users",
        produces = "application/vnd.users-v1+json",
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "username", required = false) String username) {
        User condition = new User();
        condition.setUsername(username);
        return ResponseEntity.ok().body(new ResponseBody(userService.list(condition)));
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("username") String username) {
        User user = userService.getByUsername(username);
        Preconditions.checkResourceNotFound(user);
        return ResponseEntity.ok().body(new ResponseBody(user));
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public ResponseEntity edit(@PathVariable("username") String username, @RequestBody User user) {
        user.setUsername(username);
        user.setUpdatedBy(UserUtil.getCurrentUsername());
        Preconditions.checkResourceNotFound(userService.edit(user));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{username}/roles", method = RequestMethod.GET)
    public ResponseEntity listRole(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(new ResponseBody(userService.listPrivilegesForUser(username)));
    }

    @RequestMapping(value = "/{username}/privileges", method = RequestMethod.GET)
    public ResponseEntity listPrivilege(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(new ResponseBody(userService.listPrivilegesForUser(username)));
    }

    @RequestMapping(value = "/{username}/password", method = RequestMethod.POST)
    public ResponseEntity editPassword(@PathVariable(value = "username") String username,
            @RequestParam(value = "operation") String operation, @RequestBody(required = false) Password password) {
        Preconditions.checkPrerequisite(StringUtils.equalsAny(operation, "reset", "change"));

        if (StringUtils.equals(operation, "reset")) {
            userService.resetPassword(username);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            Preconditions.checkPrerequisite(password != null || password.getNewPassword() != null);
            Preconditions.checkPrerequisite(password.getToken() != null || password.getPassword() != null);
            password.setUsername(username);

            if (StringUtils.isNotEmpty(password.getToken())) {
                userService.changePasswordWithToken(password);
            } else {
                userService.changePassword(password);
            }

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}
