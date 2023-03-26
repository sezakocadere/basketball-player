package com.basketball.basketball.service.user;

import com.basketball.basketball.dto.UserRequest;
import com.basketball.basketball.model.LoginUser;
import org.springframework.security.core.userdetails.User;

public interface UserService {
    LoginUser createUser(UserRequest userRequest);

    String login(UserRequest userRequest) throws Exception;

}
