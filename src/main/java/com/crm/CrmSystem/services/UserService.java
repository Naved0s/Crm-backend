package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.User;
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


    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findUser(long id){
        return userRepository.findById(id).orElseThrow();
    }

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
}
