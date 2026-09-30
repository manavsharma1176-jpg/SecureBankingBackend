package com.manav.securebanking.service;


import com.manav.securebanking.dto.UserRegistrationRequest;
import com.manav.securebanking.model.User;
import com.manav.securebanking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(UserRegistrationRequest request){

        User user = new User();

        user.setUsername(request.getUsername());

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "User registered Successfully";
    }



}
