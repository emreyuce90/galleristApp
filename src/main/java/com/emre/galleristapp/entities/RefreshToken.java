package com.emre.galleristapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.Date;

@Table(name="refresh_token")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends BaseClass{

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expiration_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date expirationDate;

    @OneToOne
    private User user;
}
