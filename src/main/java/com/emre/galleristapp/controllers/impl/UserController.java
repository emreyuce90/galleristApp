package com.emre.galleristapp.controllers.impl;

import com.emre.galleristapp.controllers.IUserController;
import com.emre.galleristapp.dtos.RegisterDto;
import com.emre.galleristapp.dtos.UserDto;
import com.emre.galleristapp.results.Response;
import com.emre.galleristapp.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/register")
public class UserController  implements IUserController {
    @Autowired
    private IUserService userService;


    @PostMapping( path = "")
    @Override
    public Response<UserDto> RegisterUser(@RequestBody @Valid RegisterDto registerDto) {
        return userService.RegisterUser(registerDto);
    }
}
