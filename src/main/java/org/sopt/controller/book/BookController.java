package org.sopt.controller.book;


import lombok.RequiredArgsConstructor;
import org.sopt.dto.book.response.BookResponseDetailDTO;
import org.sopt.service.book.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sopt.dto.book.response.BookResponseDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class BookController {

    private final BookService bookService;

    //@GetMapping("/health")
    //public ResponseEntity<String> test() {
    //    return ResponseEntity.ok("OK");
    //}

    @GetMapping("/books/top5")
    public List<BookResponseDTO> getTop5Books() {
        return bookService.getTop5Books();
    }

    @GetMapping("/books/{id}")
    public BookResponseDetailDTO getBookDetail(@PathVariable Long id) {
        return bookService.getBookDetail(id);
    }
}