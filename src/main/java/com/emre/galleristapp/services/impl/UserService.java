package com.emre.galleristapp.services.impl;

import com.emre.galleristapp.dtos.RegisterDto;
import com.emre.galleristapp.dtos.UserDto;
import com.emre.galleristapp.entities.User;
import com.emre.galleristapp.repositories.IUserRepository;
import com.emre.galleristapp.results.Response;
import com.emre.galleristapp.services.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private User createUser(RegisterDto registerDto){
        User user = new User();
        user.setUsername(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setDate(new Date());
        return user;
    }

    @Override
    public Response<UserDto> RegisterUser(RegisterDto registerDto) {
        User user = userRepository.save(createUser(registerDto));
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return new Response<UserDto>().success(userDto);
    }
}
