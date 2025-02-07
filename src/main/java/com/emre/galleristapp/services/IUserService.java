package com.emre.galleristapp.services;

import com.emre.galleristapp.dtos.RegisterDto;
import com.emre.galleristapp.dtos.UserDto;
import com.emre.galleristapp.results.Response;

public interface IUserService {
        Response<UserDto> RegisterUser(RegisterDto registerDto);
}
