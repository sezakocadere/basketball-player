package com.basketball.basketball.service.user;

import com.basketball.basketball.dto.UserRequest;
import com.basketball.basketball.model.LoginUser;
import com.basketball.basketball.repository.UserRepository;
import com.basketball.basketball.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public LoginUser createUser(UserRequest userRequest) {
        LoginUser user = new LoginUser();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public String login(UserRequest userRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new UsernameNotFoundException("Incorrect username or password", ex);
        }
        UserDetails userDetails = new User(userRequest.getUsername(), userRequest.getPassword(), new ArrayList<>());
        return jwtUtil.generateToken(userDetails);
    }

}
