package com.ackSystem.scms.repository;

import com.ackSystem.scms.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepo extends JpaRepository<OrderItems, String> {
}
