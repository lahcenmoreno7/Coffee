package com.coffee.assessment.repository;

import com.coffee.assessment.model.Payment;
import com.coffee.assessment.utils.CoffeeConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment, Long> {

    @Query(CoffeeConstant.FIND_TOTAL_PAID_BY_USER_QUERY)
    Double findTotalPaidByUser(@Param("username") String username);

    @Query(CoffeeConstant.FIND_PAYMENT_QUERY)
    List<Map<String, Object>> findPayments();
}
