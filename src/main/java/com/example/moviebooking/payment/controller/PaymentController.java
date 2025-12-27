package com.example.moviebooking.payment.controller;

import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.common.resposeEntity.CommonRespose;
import com.example.moviebooking.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //  CREATE ORDER
    @PostMapping("/create-order/{bookingId}")
    public ResponseEntity<CommonRespose> createOrder(
            @PathVariable Long bookingId) {

        return ResponseEntity.ok(
                new CommonRespose(false,"Payment done",paymentService.createRazorpayOrder(bookingId)));
    }

    //  VERIFY PAYMENT
    @PostMapping("/verify")
    public ResponseEntity<CommonRespose> verifyPayment(
            @RequestParam Long bookingId,
            @RequestParam String razorpayPaymentId,
            @RequestParam String razorpayOrderId,
            @RequestParam String razorpaySignature) throws DataNotFoundException {

        return ResponseEntity.ok(
                new CommonRespose(false,"payment check",paymentService.verifyPayment(
                        bookingId,
                        razorpayPaymentId,
                        razorpayOrderId,
                        razorpaySignature)));
    }
}

