package com.coffee.assessment.repository;

import com.coffee.assessment.model.Order;
import com.coffee.assessment.utils.CoffeeConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(CoffeeConstant.FIND_TOTAL_OWES_BY_USER_QUERY)
    List<Map<String, Object>> findTotalOwesByUser();
}
