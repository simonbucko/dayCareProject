package com.project.daycare.repository;

import com.project.daycare.module.Children;
import com.project.daycare.module.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Override
    List<Payment> findAll();
}
