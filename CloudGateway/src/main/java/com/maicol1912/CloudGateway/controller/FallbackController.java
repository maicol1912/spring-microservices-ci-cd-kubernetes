package com.maicol1912.CloudGateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/orderServiceFallBack")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String orderServiceFallback(){
        return "Order Service is down!";
    }

    @GetMapping("/paymentServiceFallBack")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String paymentServiceFallback(){
        return "Payment Service is down!";
    }

    @GetMapping("/productServiceFallBack")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String productServiceFallback(){
        return "Product Service is down!";
    }

    @GetMapping("/authServiceFallBack")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String authServiceFallback(){
        return "Auth Service is down!";
    }
}
