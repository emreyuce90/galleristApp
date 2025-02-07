package com.emre.galleristapp.controllers;

import com.emre.galleristapp.dtos.RegisterDto;
import com.emre.galleristapp.dtos.UserDto;
import com.emre.galleristapp.results.Response;

public interface IUserController {
    Response<UserDto> RegisterUser(RegisterDto registerDto);

}
