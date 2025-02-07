package com.emre.galleristapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="car")
public class Car extends BaseClass{
    private String plate;
    private String model;
    private String brand;
    @Column(name="production_year")
    private Integer productionYear;
    private BigDecimal price;
    @Column(name = "damage_amount")
    private BigDecimal damageAmount;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "currency_type")
    private CurrencyType currencyType;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "car_status_type")
    private CarStatusType carStatusType;


}
