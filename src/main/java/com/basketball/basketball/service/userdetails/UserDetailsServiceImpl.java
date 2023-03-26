package com.basketball.basketball.service.userdetails;

import com.basketball.basketball.model.LoginUser;
import com.basketball.basketball.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, getByUsername(username).getPassword(), new ArrayList<>());
    }

    private LoginUser getByUsername(String username) {
        return userRepository.findOneByUsername(username).orElseThrow();
    }
}
