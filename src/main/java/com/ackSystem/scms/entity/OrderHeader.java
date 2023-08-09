package com.ackSystem.scms.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHeader {

    @Id
    private String userId;

    private String userName;

    private String userMob;

    private String userEmail;

    private String purchesedOrderNumber;

    private LocalDate purchaseOrderDate;

    @OneToOne(cascade = CascadeType.ALL)
    private OrderItems orderItems;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
