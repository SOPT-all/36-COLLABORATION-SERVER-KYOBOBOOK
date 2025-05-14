package org.sopt.service.book;

import org.sopt.domain.book.Book;
import org.sopt.dto.book.response.BookResponseDTO;
import org.sopt.repository.book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //상위 5개만 조회하는 비즈니스로직
    private static final int TOP_BOOK_LIMIT = 5;

    public List<BookResponseDTO> getTop5Books(){
        List<Book> books = bookRepository.findAll();
        int limit = Math.min(TOP_BOOK_LIMIT, books.size());
        List<Book> top5Books = books.subList(0, limit);
        return top5Books.stream().map(book -> new BookResponseDTO(book.getRanking(),book.getTitle(),book.getAuthor(),book.getPublisher())).toList();
    }

}