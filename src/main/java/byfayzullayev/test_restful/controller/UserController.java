package byfayzullayev.test_restful.controller;



import byfayzullayev.test_restful.model.receive.SingInReceiveModel;
import byfayzullayev.test_restful.model.receive.SingUpReceiveModel;
import byfayzullayev.test_restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/test/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpEntity<?> addUser(@Valid @RequestBody SingUpReceiveModel singUpReceiveModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(singUpReceiveModel));
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody SingInReceiveModel singInReceiveModel) {
        return ResponseEntity.ok(userService.login(singInReceiveModel));
    }

}

