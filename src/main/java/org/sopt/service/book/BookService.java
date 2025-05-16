package org.sopt.service.book;

import org.sopt.domain.book.Book;
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
        List<Book> books = bookRepository.findAll();
        int limit = Math.min(TOP_BOOK_LIMIT, books.size());
        List<Book> top5Books = books.subList(0, limit);

        return top5Books.stream().map(book -> new BookResponseDTO(book.getRanking(),book.getTitle(),book.getAuthor(),book.getPublisher())).toList();
    }

    //도서 상세 조회
    public BookDetailResponseDTO getBookDetail(Long bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException(BookErrorMessage.NOT_FOUND_BOOK));

        if (book.getBookIndex() == null || book.getBookIndex().isBlank()) {
            throw new BusinessException(BookErrorMessage.EMPTY_BOOK_INDEX);
        }

        List<String> indexList = Arrays.stream(book.getBookIndex().split("\n"))
                .filter(line -> !line.trim().isEmpty())
                .toList();

        return new BookDetailResponseDTO(book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishDate(),book.getPrice() + "원", book.getDescription().replace("\n", " "), indexList);

    }
}