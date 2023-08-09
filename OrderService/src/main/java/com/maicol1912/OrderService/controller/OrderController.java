package com.maicol1912.OrderService.controller;

import com.maicol1912.OrderService.model.OrderRequest;
import com.maicol1912.OrderService.model.OrderResponse;
import com.maicol1912.OrderService.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long>placeOrder(@RequestBody OrderRequest orderRequest){
        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order id: {}",orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse>getOrderDetails(@PathVariable("orderId")long orderId){
          OrderResponse orderResponse
                = orderService.getOrderDetails(orderId);
          return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }
}
