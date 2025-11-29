package dev.cuong.smartbookstore.orderservice.order.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
}
