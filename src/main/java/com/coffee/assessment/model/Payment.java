package com.coffee.assessment.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String user;
    private double amount;

}
