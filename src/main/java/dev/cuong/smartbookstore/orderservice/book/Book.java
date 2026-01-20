package dev.cuong.smartbookstore.orderservice.book;

/**
 * A DTO class holding basic book info for an order.
 *
 * @param isbn
 * @param title
 * @param author
 * @param price
 */
public record Book(
    String isbn,
    String title,
    String author,
    Double price
) {
}
