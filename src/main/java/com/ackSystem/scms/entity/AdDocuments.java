package com.ackSystem.scms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userName;

    private Long productNumber;

    private String purchesedOrderNumber;

    private LocalDate documentCreatedDate;

    private String acknowledgementType;

    @OneToOne
    private OrderItems orderItems;


}
