package com.manav.securebanking.service;


import com.manav.securebanking.dto.LoginRequest;
import com.manav.securebanking.dto.UserRegistrationRequest;
import com.manav.securebanking.exception.InvalidCredentialsException;
import com.manav.securebanking.model.User;
import com.manav.securebanking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.manav.securebanking.service.JwtService;
;


@Service
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

    public String loginUser(LoginRequest request){

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->
                       new RuntimeException("Invalid username or password"));

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if(!passwordMatches){
            throw new InvalidCredentialsException("Invalid username or password");

        } return jwtService.generateToken(user.getUsername());




    }



}
