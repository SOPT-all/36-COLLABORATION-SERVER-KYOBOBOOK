package org.sopt.service.book;

import org.sopt.domain.book.Book;
import org.sopt.dto.book.response.BookResponseDTO;
import org.sopt.repository.book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<BookResponseDTO> getAllBooks(){
        List<Book> books = bookRepository.findAll();


        //상위 5개만 조회하는 비즈니스로직 추가해야함


        return books.stream().map(book -> new BookResponseDTO(book.getTitle(),book.getAuthor(),book.getPublisher())).toList();
    }
}
        //String title, Long rank, String author, String publisher