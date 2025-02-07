package com.emre.galleristapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Aynı kayıttan tekrar oluşmasın
@Table(name = "saled_car",uniqueConstraints={@UniqueConstraint(columnNames = {"gallerist_id","car_id","customer_id"})})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaledCar extends BaseClass {

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Gallerist gallerist;
}
