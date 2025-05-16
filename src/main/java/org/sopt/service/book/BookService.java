package org.sopt.service.book;

import org.sopt.domain.book.entity.BookEntity;
import org.sopt.dto.book.response.BookDetailResponseDTO;
import org.sopt.dto.book.response.BookResponseDTO;
import org.sopt.global.exception.BusinessException;
import org.sopt.repository.book.BookRepository;
import org.springframework.stereotype.Service;
import org.sopt.global.message.business.BookErrorMessage;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //상위 5개 베스트도서 조회
    private static final int TOP_BOOK_LIMIT = 5;

    public List<BookResponseDTO> getTop5Books(){
        List<BookEntity> bookEntities = bookRepository.findAll();
        int limit = Math.min(TOP_BOOK_LIMIT, bookEntities.size());
        List<BookEntity> top5BookEntities = bookEntities.subList(0, limit);

        return top5BookEntities.stream().map(bookEntity -> new BookResponseDTO(bookEntity.getRanking(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPublisher())).toList();
    }

    //도서 상세 조회
    public BookDetailResponseDTO getBookDetail(Long bookId){
        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException(BookErrorMessage.NOT_FOUND_BOOK));

        if (bookEntity.getBookIndex() == null || bookEntity.getBookIndex().isBlank()) {
            throw new BusinessException(BookErrorMessage.EMPTY_BOOK_INDEX);
        }

        List<String> indexList = Arrays.stream(bookEntity.getBookIndex().split("\n"))
                .filter(line -> !line.trim().isEmpty())
                .toList();

        return new BookDetailResponseDTO(bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPublisher(), bookEntity.getPublishDate(), bookEntity.getPrice() + "원", bookEntity.getDescription().replace("\n", " "), indexList);

    }
}