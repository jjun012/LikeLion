package myFristSpring.HelloSpring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidArticleIdException.class)
    public ResponseEntity<String> InvalidArticleId(InvalidArticleIdException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("없는 articleId");
    }
    @ExceptionHandler(InvalidCommentIdException.class)
    public ResponseEntity<String> InvalidCommentId(InvalidCommentIdException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("없는 commentId");
    }
    @ExceptionHandler(InvalidComIdMemException.class)
    public ResponseEntity<String> InvalidComIdMem(InvalidComIdMemException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("당신의 글이 아닙니다");
    }
}
