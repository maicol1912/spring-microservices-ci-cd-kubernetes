package com.maicol1912.OrderService.service;


import com.maicol1912.OrderService.model.OrderRequest;
import com.maicol1912.OrderService.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
