package org.sopt.controller.book;

import org.sopt.dto.book.response.BookResponseDTO;
import org.sopt.service.book.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public List<BookResponseDTO> getTopFiveBooks(){
        return bookService.getAllBooks();
    }

}
