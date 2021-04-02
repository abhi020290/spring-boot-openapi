package com.springbootopenapi.springbootopenapi.repository;

import com.springbootopenapi.springbootopenapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {
    Optional<Payment> findByPaymentId(long paymentId);

}
