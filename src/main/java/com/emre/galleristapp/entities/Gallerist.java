package com.emre.galleristapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "gallerist")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Gallerist extends BaseClass {
   @Column(name = "first_name")
    private String firstName;
   @Column(name = "last_name")
    private String lastName;
   @OneToOne
   private Address address;

   @OneToMany
   private List<Car> cars;


}
