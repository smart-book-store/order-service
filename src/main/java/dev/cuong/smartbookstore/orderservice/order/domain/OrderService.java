package dev.cuong.smartbookstore.orderservice.order.domain;

import dev.cuong.smartbookstore.orderservice.book.BookClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Business logics for Order service.
 */
@Service
public class OrderService {
  private final BookClient bookClient;
  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository, BookClient bookClient) {
    this.bookClient = bookClient;
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
   * @param isbn     The book isbn to be ordered
   * @param quantity The number of copies to be ordered
   * @return Mono emitting the saved order
   */
  public Mono<Order> submitOrder(String isbn, int quantity) {
    // call catalog service to check whether or not the book is available by given
    // isbn
    return bookClient.getBookByIsbn(isbn)
        .map(book -> buildOrder(isbn, quantity, book.title() + book.author(), book.price(), OrderStatus.ACCEPTED))
        .defaultIfEmpty(
            buildOrder(isbn, quantity, null, null, OrderStatus.REJECTED))
        .flatMap(orderRepository::save);
  }

  private static Order buildOrder(String isbn, int quantity, String bookTitle, Double price, OrderStatus status) {
    return Order.of(isbn, bookTitle, price, quantity, status);
  }
}
