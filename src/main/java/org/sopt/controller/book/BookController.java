package org.sopt.controller.book;

import lombok.RequiredArgsConstructor;
import org.sopt.dto.book.response.BookDetailResponseDTO;
import org.sopt.dto.book.response.BookTotalResponseDTO;
import org.sopt.dto.review.response.BookDetailListResponseDTO;
import org.sopt.global.dto.ResponseDTO;
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
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;


    @GetMapping("/top5")
    public ResponseEntity<ResponseDTO<List<BookResponseDTO>>> getTop5Books() {
        return ResponseEntity.ok(ResponseDTO.success(bookService.getTop5Books()));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<BookTotalResponseDTO>>>getAllBooks() {
        return ResponseEntity.ok(ResponseDTO.success(bookService.getAllBooks()));
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<ResponseDTO<BookDetailResponseDTO>> getBookDetail(@PathVariable Long bookId) {
        return ResponseEntity.ok(ResponseDTO.success(bookService.getBookDetail(bookId)));
    }
}