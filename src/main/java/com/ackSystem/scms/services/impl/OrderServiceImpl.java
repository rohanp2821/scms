package com.ackSystem.scms.services.impl;

import com.ackSystem.scms.entity.AdDocuments;
import com.ackSystem.scms.help.BuyOrder;
import com.ackSystem.scms.entity.OrderHeader;
import com.ackSystem.scms.entity.ProductInfo;
import com.ackSystem.scms.repository.AdDocumentsRepo;
import com.ackSystem.scms.repository.OrderHeaderRepository;
import com.ackSystem.scms.repository.ProductInfoRepo;
import com.ackSystem.scms.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Autowired
    private ProductInfoRepo productInfoRepo;

    @Autowired
    private AdDocumentsRepo adDocumentsRepo;


    @Override
    public OrderHeader saveOrder(OrderHeader orderHeader) {
        String id = UUID.randomUUID().toString();
        logger.info("User ID : {}",id);
        String purchesedOrderNumber = UUID.randomUUID().toString();
        logger.info("Purchased Date : {}",purchesedOrderNumber);
        LocalDate now = LocalDate.now();
        orderHeader.setUserId(id);
        orderHeader.setPurchaseOrderDate(now);
        orderHeader.setPurchesedOrderNumber(purchesedOrderNumber);
        logger.info("Total Order Header : {}",orderHeader);
        return orderHeaderRepository.save(orderHeader);
    }

    @Override
    public OrderHeader getOrder(String userId) {
        return orderHeaderRepository.findById(userId).orElseThrow();
    }

    @Override
    public ProductInfo saveProduct(ProductInfo productInfo) {

        String id = UUID.randomUUID().toString();
        productInfo.setId(id);
        return productInfoRepo.save(productInfo);
    }


    @Override
    public AdDocuments AdDocumentGenerateAndPersist(BuyOrder order) {


        OrderHeader orderHeader = orderHeaderRepository.findById(order.getUserId()).orElseThrow(()-> new RuntimeException("User Not found with this ID !!"));

        ProductInfo productInfo = productInfoRepo.findByProductNumber(order.getProductNumber());

        AdDocuments adDocuments = new AdDocuments();

        LocalDate documentCreatedDate = LocalDate.now();


        adDocuments.setDocumentCreatedDate(documentCreatedDate);


        int userProductQty = orderHeader.getOrderItems().getProductQty();
        int stockProductQty = productInfo.getProductQty();

        if (userProductQty > stockProductQty) {

            adDocuments.setUserName(orderHeader.getUserName());
            adDocuments.setProductNumber(productInfo.getProductNumber());
            adDocuments.setPurchesedOrderNumber(orderHeader.getPurchesedOrderNumber());
            adDocuments.setAcknowledgementType("Rejected Details");
            adDocuments.setOrderItems(orderHeader.getOrderItems());

        }
        if (userProductQty <= stockProductQty) {

            adDocuments.setUserName(orderHeader.getUserName());
            adDocuments.setProductNumber(productInfo.getProductNumber());
            adDocuments.setPurchesedOrderNumber(orderHeader.getPurchesedOrderNumber());
            adDocuments.setAcknowledgementType("Accept Details");
            adDocuments.setOrderItems(orderHeader.getOrderItems());

            changeItemsInProductInfo(productInfo, stockProductQty, userProductQty);


        }

            return adDocumentsRepo.save(adDocuments);

    }

    private ProductInfo changeItemsInProductInfo(ProductInfo productInfo ,int stockProductQty, int userProductQty) {

        productInfo.setProductQty(stockProductQty-userProductQty);
        deleteProductInfo(productInfo.getId());
        return productInfoRepo.save(productInfo);
    }
    private void deleteProductInfo (String id) {
        ProductInfo productInfoById = productInfoRepo.findById(id).orElseThrow();
        productInfoRepo.delete(productInfoById);
    }
}
