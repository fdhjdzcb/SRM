package com.AMIR.SRM.repositories;

import com.AMIR.SRM.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
