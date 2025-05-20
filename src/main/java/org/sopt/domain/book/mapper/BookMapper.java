package org.sopt.domain.book.mapper;

import org.sopt.domain.book.entity.BookEntity;
import org.sopt.domain.book.model.Book;

public class BookMapper {
    public static Book toDomain(BookEntity entity) {
        if (entity == null) return null;

        return new Book(
                entity.getBookId(),
                entity.getTitle(),
                entity.getPrice(),
                entity.getPublisher(),
                entity.getBookIndex(),
                entity.getDescription(),
                entity.getPublishDate(),
                entity.getAuthor(),
                entity.getRanking(),
                entity.getImageUrl()
        );
    }
}
