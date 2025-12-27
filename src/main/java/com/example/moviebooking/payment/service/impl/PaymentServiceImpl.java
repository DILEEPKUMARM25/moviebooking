package com.example.moviebooking.payment.service.impl;

import com.example.moviebooking.booking.entity.Booking;
import com.example.moviebooking.booking.repository.BookingRepository;
import com.example.moviebooking.common.enums.PaymentProvider;
import com.example.moviebooking.common.enums.PaymentStatus;
import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.payment.entity.Payment;
import com.example.moviebooking.payment.repository.PaymentRepository;
import com.example.moviebooking.payment.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RazorpayClient razorpayClient;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Map<String, Object> createRazorpayOrder(Long bookingId)  {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new DataNotFoundException("Booking not found"));

        try {
            JSONObject options = new JSONObject();
            options.put("amount", booking.getTotalAmount() * 100); // paise
            options.put("currency", "INR");
            options.put("receipt", "booking_" + bookingId);

            // Razorpay Order (NOT JPA Order)
            Order razorpayOrder = razorpayClient.orders.create(options);

            Payment payment = new Payment();
            payment.setBookingId(bookingId);
            payment.setAmount(booking.getTotalAmount());
            payment.setProvider(PaymentProvider.RAZORPAY);
            payment.setStatus(PaymentStatus.INITIATED);
            payment.setTransactionId(razorpayOrder.get("id"));

            paymentRepository.save(payment);

            Map<String, Object> response = new HashMap<>();
            response.put("orderId", razorpayOrder.get("id"));
            response.put("amount", booking.getTotalAmount());
            response.put("currency", "INR");

            return response;

        } catch (RazorpayException e) {
            throw new DataNotFoundException("Failed to create Razorpay order");
        }
    }

    // REQUIRED BY INTERFACE
    @Override
    public Payment verifyPayment(Long bookingId,
                                 String razorpayPaymentId,
                                 String razorpayOrderId,
                                 String razorpaySignature)  {

        Payment payment = paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new DataNotFoundException("Payment not found"));

        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId(razorpayPaymentId);

        return paymentRepository.save(payment);
    }
}
