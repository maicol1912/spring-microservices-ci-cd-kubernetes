package com.maicol1912.PaymentService.service;

import com.maicol1912.PaymentService.model.PaymentRequest;
import com.maicol1912.PaymentService.model.PaymentResponse;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
