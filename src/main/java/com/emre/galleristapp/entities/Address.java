package com.emre.galleristapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "adress")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseClass{
    private String street;
    private String city;
    private String state;
    private String zip;
}
