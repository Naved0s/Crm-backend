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


//   @PostMapping("/signup")
//   public String signup(
//           @RequestParam String email,
//           @RequestParam String password,
//           @RequestParam String name,
//           @RequestParam long roleid) {
//       Role role = roleService.getById(roleid);
//       User user = new User(email, password, name, role);
//       userService.addUser(user);
//       return "Signup Done";
//   }


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


//   @PostMapping("/Signup")
//    public String SignUp(@RequestBody User user){
//       userService.addUser(user);
//       return "User Register Successfully!";
////       "userName":"pojo",
////               "password":"1234",
////               "emailId":"pojo@gmail.com",
////               "role":{
////           "roleId":1
////       }
//    }
//
//    @GetMapping("/userid")
//    public String getUser(@RequestParam long id) {
//        User user = userService.findUser(id);
//        return user.getUsername() + user.getEmailId() + user.getRole().getRoleName();
//    }

//    @PostMapping("/login")
//    public String login(@RequestParam  String username ,@RequestParam String password){
//        System.out.println(username+ " "+ password);
//       return userService.login(username,password).getUsername();
//    }

    @PostMapping("/Login")
    public User loginU(@RequestBody User user){
       return userService.login(user.getEmailId(),user.getPassword()) ;
    }

}
