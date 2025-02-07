package com.emre.galleristapp.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty(message = "username alanı boş geçilemez")
    private String userName;

    @NotEmpty(message = "password alanı boş geçilemez")
    private String password;
}
