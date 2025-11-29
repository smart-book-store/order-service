package dev.cuong.smartbookstore.orderservice.order.web;

import dev.cuong.smartbookstore.orderservice.order.domain.Order;
import dev.cuong.smartbookstore.orderservice.order.domain.OrderService;
import jakarta.validation.Valid;
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
  private final OrderService orderService;

  OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  Flux<Order> getAllOrders() {
    return orderService.getAllOrders();
  }

  @PostMapping
  Mono<Order> submitOrder(@RequestBody @Valid OrderRequest orderRequest) {
    return orderService.submitOrder(orderRequest.isbn(), orderRequest.quantity());
  }
}
