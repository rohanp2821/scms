package com.ackSystem.scms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int OrderId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productNumber;

    private String productDescription;

    private int productQty;

    private Long productPrice;


}