package com.emre.galleristapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "account")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseClass{
    @Column(name="account_number")
    private String accountNumber;
    private String iban;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_type")
    private CurrencyType currencyType;
}
