package com.lmig.gfc.wimp.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.User;
import com.lmig.gfc.wimp.repositories.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public UserApiController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping("")
    public User getUser(Authentication auth) {
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        
        return userRepository.findByUsername(username);
    }

    @PutMapping("")
    public User updateUser(Authentication auth, @RequestBody User user) {

    	User loggedInUser = (User) auth.getPrincipal();
    	user.setId(loggedInUser.getId());
    	    	
    	return userRepository.save(user);
    }

    @PostMapping("create")
    public User createUser(@RequestBody User user) {
        String password = user.getPassword();
        String encryptedPassword = encoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepository.save(user);        
        return user;
    }
}

