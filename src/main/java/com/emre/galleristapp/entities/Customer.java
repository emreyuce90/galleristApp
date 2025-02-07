package com.emre.galleristapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Table(name = "customer")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseClass {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String tckn;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToOne
    private Address address;

    @OneToOne
    private Account account;
}
