package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Role;
import com.crm.CrmSystem.models.User;
import com.crm.CrmSystem.repository.RoleRepository;
import com.crm.CrmSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    //Register User
    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        Role role = roleRepository.findById(user.getRole().getRoleId())
//                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Set the fetched role to the user
      //  user.setRole(role);
        return userRepository.save(user);
    }

    //Find User
    public User findUser(long id){
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    //Login
    public User login(String email , String password){
            User u = userRepository.findByEmailId(email).orElseThrow();
            String pass = u.getPassword();
        System.out.println(email+password);
            if(passwordEncoder.matches(password,pass)){
                return u;
            }
            else{
                return null;
            }
    }





    /************************** Login with Username ****************/
//    public User loginName(String username , String password){
//        User u = userRepository.findByUserName(username).orElseThrow();
//        String pass = u.getPassword();
//        System.out.println(username+password);
//        if(passwordEncoder.matches(password,pass)){
//            return u;
//        }
//        else{
//            return null;
//        }
//    }
}
