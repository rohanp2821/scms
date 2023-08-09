package com.ackSystem.scms.services;

import com.ackSystem.scms.entity.AdDocuments;
import com.ackSystem.scms.help.BuyOrder;
import com.ackSystem.scms.entity.OrderHeader;
import com.ackSystem.scms.entity.ProductInfo;

public interface OrderService {

    public OrderHeader saveOrder(OrderHeader orderHeader);

    public OrderHeader getOrder(String userId);

    public ProductInfo saveProduct(ProductInfo productInfo);

    public AdDocuments AdDocumentGenerateAndPersist(BuyOrder order);

}
