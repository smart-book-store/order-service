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
 * @param bookName
 * @param bookPrice
 * @param quantity
 * @param status
 * @param createdDate
 * @param lastModifiedDate
 * @param version
 */
@Table("orders")
public record Order(
    @Id
    Long id,
    String bookIsbn,
    String bookName,
    Double bookPrice,
    Integer quantity,
    OrderStatus status,
    @CreatedDate
    Instant createdDate,
    @LastModifiedDate
    Instant lastModifiedDate,
    @Version
    int version) {
  public static Order of(String bookIsbn, String bookName, Double price, Integer quantity, OrderStatus status) {
    return new Order(null, bookIsbn, bookName, price, quantity, status, null, null, 0);
  }
}
