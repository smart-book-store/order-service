package dev.cuong.smartbookstore.orderservice.order.domain;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Business logics for Order service.
 */
@Service
public class OrderService {
  private final OrderRepository orderRepository;

  OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  /**
   * Get all orders in current system.
   *
   * @return Flux emitting all orders from the system
   */
  public Flux<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  /**
   * Create an order on given book (isbn and quantity).
   *
   * @param isbn The book isbn to be ordered
   * @param quantity The number of copies to be ordered
   * @return Mono emitting the saved order
   */
  public Mono<Order> submitOrder(String isbn, int quantity) {
    return Mono.just(buildOrder(isbn, quantity, OrderStatus.REJECTED)).flatMap(orderRepository::save);
  }

  private static Order buildOrder(String isbn, int quantity, OrderStatus status) {
    return Order.of(isbn, null, null, quantity, status);
  }
}
