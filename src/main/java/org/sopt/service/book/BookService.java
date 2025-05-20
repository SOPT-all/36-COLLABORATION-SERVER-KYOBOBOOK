package org.sopt.service.book;

import org.sopt.domain.book.entity.BookEntity;
import org.sopt.domain.book.model.Book;
import org.sopt.domain.review.entity.statistic.ReviewStatisticEntity;
import org.sopt.dto.book.response.BookDetailResponseDTO;
import org.sopt.dto.book.response.BookResponseDTO;
import org.sopt.dto.book.response.BookTotalResponseDTO;
import org.sopt.dto.review.response.BookDetailListResponseDTO;
import org.sopt.global.exception.BusinessException;
import org.sopt.repository.Review.ReviewStatisticRepository;
import org.sopt.repository.book.BookRepository;
import org.springframework.stereotype.Service;
import org.sopt.global.message.business.BookErrorMessage;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private ReviewStatisticRepository reviewStatisticRepository;

    public BookService(BookRepository bookRepository, ReviewStatisticRepository reviewStatisticRepository){
        this.bookRepository = bookRepository;
        this.reviewStatisticRepository = reviewStatisticRepository;
    }

    //상위 5개 베스트도서 조회
    private static final int TOP_BOOK_LIMIT = 5;

    public List<BookResponseDTO> getTop5Books(){
        List<BookEntity> bookEntities = bookRepository.findAll();
        int limit = Math.min(TOP_BOOK_LIMIT, bookEntities.size());
        List<BookEntity> top5BookEntities = bookEntities.subList(0, limit);

        return top5BookEntities.stream().map(bookEntity -> new BookResponseDTO(bookEntity.getRanking(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPublisher(), bookEntity.getImageUrl())).toList();
    }

    public List<BookTotalResponseDTO> getAllBooks() {
        List<BookEntity> books = bookRepository.findAll();

        return books.stream()
                .map(book -> {
                    Optional<ReviewStatisticEntity> statOpt = reviewStatisticRepository.findByBook_BookId(book.getBookId());

                    // 평균 별점과 감정 처리
                    int averageStar = statOpt
                            .map(stat -> stat.getAverageStar() != null ? stat.getAverageStar().intValue() : 0)
                            .orElse(0);

                    String averageEmotion = statOpt
                            .map(ReviewStatisticEntity::getAverageEmotion)
                            .filter(emotion -> !emotion.isBlank()) // 공백 방지
                            .orElse("기타"); // 기본 감정

                    return new BookTotalResponseDTO(
                            book.getBookId(),
                            book.getTitle(),
                            book.getPrice(),
                            book.getAuthor(),
                            book.getPublisher(),
                            book.getRanking(),
                            book.getImageUrl(),
                            averageStar,
                            averageEmotion
                    );
                })
                .toList();
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



        return new BookDetailResponseDTO(bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPublisher(), bookEntity.getPublishDate(), bookEntity.getPrice() + "원", bookEntity.getDescription().replace("\n", " "), bookEntity.getImageUrl(), indexList);

    }
}