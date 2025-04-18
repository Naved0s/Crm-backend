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


   @PostMapping("/Signup")
    public User SignUp(@RequestBody User user){
       System.out.println(user.getEmailId()+user.getRole()+user.getAuthorities());
      return userService.addUser(user);
//       "userName":"pojo",
//               "password":"1234",
//               "emailId":"pojo@gmail.com",
//               "role":{
//           "roleId":1
//       }
    }

    public ResponseEntity<Map<String,Object>> signUp(@RequestBody User user){
        User savedUser = userService.addUser(user);
        Map<String,Object> respone = new HashMap<>();
        respone.put("mesg","Signup sucess");
        return ResponseEntity.ok(respone);
    }


    @PostMapping("/Login")
    public User loginU(@RequestBody User user){
       return userService.login(user.getEmailId(),user.getPassword()) ;
    }

}
