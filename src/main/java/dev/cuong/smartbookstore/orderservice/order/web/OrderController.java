package dev.cuong.smartbookstore.orderservice.order.web;

import dev.cuong.smartbookstore.orderservice.order.domain.Order;
import dev.cuong.smartbookstore.orderservice.order.domain.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
class OrderController {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
  private final OrderService orderService;

  OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  Flux<Order> getAllOrders() {
    LOGGER.info("Getting all orders in the system");
    return orderService.getAllOrders();
  }

  @PostMapping
  Mono<Order> submitOrder(@RequestBody @Valid OrderRequest orderRequest) {
    LOGGER.info("Submitting an order");
    return orderService.submitOrder(orderRequest.isbn(), orderRequest.quantity());
  }
}
