package com.ackSystem.scms.repository;

import com.ackSystem.scms.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInfoRepo extends JpaRepository<ProductInfo, String> {

    public ProductInfo findByProductNumber(Long productNumber);

}
