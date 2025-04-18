package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Role;
import com.crm.CrmSystem.models.User;
import com.crm.CrmSystem.repository.RoleRepository;
import com.crm.CrmSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    public User addUser(User user){
        if(userRepository.findByEmailId(user.getEmailId()).isEmpty()){
            long roleId = user.getRole().getRoleId();
            Role fullRole = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(fullRole);
            System.out.println(user.getRole().getRoleName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
          return  userRepository.save(user);
        }
        else{
            throw new RuntimeException("User alredy exists");
        }


    }

    public User findUser(long id){
        return userRepository.findById(id).orElseThrow();
    }

    public User login(String email , String password){
            User u = userRepository.findByEmailId(email).orElseThrow();
            String pass = u.getPassword();
        System.out.println(email+password+u.getRole().getRoleName());
            if(passwordEncoder.matches(password,pass)){
                return u;
            }
            else{
                throw new RuntimeException("Please enter valid email or password");
            }
    }
}
