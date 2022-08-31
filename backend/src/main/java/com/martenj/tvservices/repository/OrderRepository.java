package com.martenj.tvservices.repository;

import com.martenj.tvservices.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByStatus(String status);

    @Async
    @Modifying
    @Query("update Order o set o.status = :status where o.status = 'New'")
    void setOrderStatus(@Param("status") String status);
}
