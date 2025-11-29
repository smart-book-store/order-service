package dev.cuong.smartbookstore.orderservice.order.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * A domain entity for holding an order. For convenience and simplicity, we will use it as a persistent entity
 *
 * @param id
 * @param bookIsbn
 * @param bookTitle
 * @param price
 * @param quantity
 * @param orderStatus
 * @param createdDate
 * @param lastModifiedDate
 * @param version
 */
@Table("orders")
public record Order(
    @Id
    Long id,
    String bookIsbn,
    String bookTitle,
    Double price,
    Integer quantity,
    OrderStatus orderStatus,
    @CreatedDate
    Instant createdDate,
    @LastModifiedDate
    Instant lastModifiedDate,
    @Version
    int version) {
  public static Order of(String bookIsbn, String bookTitle, Double price, Integer quantity, OrderStatus status) {
    return new Order(null, bookIsbn, bookTitle, price, quantity, status, null, null, 0);
  }
}
