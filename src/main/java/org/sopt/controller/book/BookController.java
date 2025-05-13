package org.sopt.controller.book;

import lombok.RequiredArgsConstructor;
import org.sopt.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;


    @GetMapping("/health")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("OK");
    }
}
