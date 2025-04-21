package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.Role;
import com.crm.CrmSystem.models.User;
import com.crm.CrmSystem.services.RoleService;
import com.crm.CrmSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    //SIGNUP MAPPING
    @PostMapping("/Signup")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody User user) {
        System.out.println(user.getEmailId() + user.getRole() + user.getAuthorities());

        User savedUser = userService.addUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Signup successful");
        response.put("userId", savedUser.getUserId()); // optional
        response.put("userName", savedUser.getUsername());

        return ResponseEntity.ok(response);
    }

    //LOGIN MAPPING
    @PostMapping("/Login")
    public User loginU(@RequestBody User user){
       return userService.login(user.getEmailId(),user.getPassword()) ;
    }




    /********* login with userName *************/
//    @PostMapping("/LoginU")
//    public User loginName(@RequestBody User user){
//    return userService.loginName(user.getUsername(),user.getPassword());
//    }

}
