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

    public List<BookResponseDTO> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book -> new BookResponseDTO(book.getTitle(),book.getAuthor(),book.getPublisher())).toList();
    }

    //상위 5개만 조회하는 비즈니스로직
    public List<BookResponseDTO> getTop5Books(){
        List<Book> books = bookRepository.findAll();
        List<Book> top5books = new ArrayList<>(books.subList(0,5));
        return top5books.stream().map(book -> new BookResponseDTO(book.getTitle(),book.getAuthor(),book.getPublisher())).toList();
    }

}
        //String title, Long rank, String author, String publisher