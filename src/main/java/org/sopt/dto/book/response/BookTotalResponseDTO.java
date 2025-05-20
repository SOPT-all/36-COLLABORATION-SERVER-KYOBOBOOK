package org.sopt.dto.book.response;

public record BookTotalResponseDTO(    Long bookId,
                                           String title,
                                           String price,
                                           String author,
                                           String publisher,
                                           Long ranking,
                                           String imageUrl,
                                           int star,
                                           String bestEmotion) {
}
