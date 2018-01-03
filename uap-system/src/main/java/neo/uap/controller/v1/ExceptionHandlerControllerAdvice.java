package neo.uap.controller.v1;

import neo.uap.domain.common.ResponseBody;
import neo.uap.exception.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler(ResourceNotAccessibleException.class)
    public ResponseEntity resourceNotAccessible() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBody("Resource not Found"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity resourceNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBody("Resource not Found"));
    }

    @ExceptionHandler(PrerequisiteNotSatisfiedException.class)
    public ResponseEntity prerequisiteNotSatisfied() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBody("Prerequisite not Satisfied"));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity wrongPassword() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBody("Wrong Password"));
    }

    @ExceptionHandler(WrongPasswordTokenException.class)
    public ResponseEntity wrongPasswordToken() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBody("Wrong Password Token"));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity duplicateKey() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBody("Entry Already Existed"));
    }
}
