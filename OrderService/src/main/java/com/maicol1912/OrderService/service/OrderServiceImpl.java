package com.maicol1912.OrderService.service;

import com.maicol1912.OrderService.entity.Order;
import com.maicol1912.OrderService.exception.CustomException;
import com.maicol1912.OrderService.external.client.PaymentService;
import com.maicol1912.OrderService.external.client.ProductService;
import com.maicol1912.OrderService.external.request.PaymentRequest;
import com.maicol1912.OrderService.external.response.PaymentResponse;
import com.maicol1912.OrderService.model.OrderRequest;
import com.maicol1912.OrderService.model.OrderResponse;
import com.maicol1912.OrderService.model.ProductResponse;
import com.maicol1912.OrderService.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${microservices.product}")
    private String productServiceUrl;
    @Value("${microservices.payment}")
    private String paymenServiceUrl;
    @Override
    public long placeOrder(OrderRequest orderRequest) {

        log.info("Placing Order Request: {}",orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        log.info("Creating Order with Status CREATED");
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        order = orderRepository.save(order);
        log.info("Calling Payment Service to complete the payment");
        PaymentRequest paymentRequest =
                PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();
        String orderStatus = null;
        try{
            paymentService.doPayment(paymentRequest);
            log.info("Payment done Successfully. Changing the Order status");
            orderStatus = "PLACED";
        }catch (Exception e){
            log.error("Error Ocurred in Payment,Changing Order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        log.info("Order Places successfully with Order Id: {}",order.getId());
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get Order details for Order Id: {}",orderId);

        Order order
                = orderRepository.findById(orderId)
                .orElseThrow(()-> new CustomException("Order not found for the order id given",
                        "NOT_FOUND",
                        404));

        log.info("Invoking Product Service to fetch the product for id {}",order.getProductId());
        ProductResponse  productResponse
                = restTemplate.getForObject(
                        productServiceUrl+order.getProductId(),
                ProductResponse.class
        );

        log.info("Getting payment from the payment service");
        PaymentResponse paymentResponse
                = restTemplate.getForObject(
                paymenServiceUrl + "order/"+order.getId(),
                PaymentResponse.class
        );

        OrderResponse.ProductDetails productDetails
                = OrderResponse.ProductDetails.builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .build();
        OrderResponse.PaymentDetails paymentDetails
                = OrderResponse.PaymentDetails
                .builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentStatus(paymentResponse.getStatus())
                .PaymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();
        return OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();
    }
}
