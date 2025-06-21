package myFristSpring.HelloSpring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import myFristSpring.HelloSpring.DTO.CommentDTO;
import myFristSpring.HelloSpring.domain.Comment;
import myFristSpring.HelloSpring.security.JwtUtility;
import myFristSpring.HelloSpring.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "댓글 관련")
public class CommentController {
    private final CommentService commentService;
    private final JwtUtility jwtUtility;

    @Operation(summary = "댓글 생성", description = "Header에 token 필요, body에 json 형태로 게시물 Id, 댓글 내용 필요",
        responses = {@ApiResponse(responseCode = "201",description = "댓글 생성"),
            @ApiResponse(responseCode = "404",description = "없는 articleId")})

    @PostMapping("/comment")
    public ResponseEntity<CommentDTO.ResComment> createComment(@RequestHeader("Authorization") String token, @RequestBody CommentDTO.CommentCreateReq request){
        jwtUtility.validateToken(token);
        Comment comment = commentService.saveComment(token, request.getArticleId(), request.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentDTO.ResComment(comment));
    }

    @PutMapping("/comment")
    public ResponseEntity<CommentDTO.ResComment> updateComment(@RequestHeader("Authorization") String token, @RequestBody CommentDTO.CommentUpdateReq request){
        jwtUtility.validateToken(token);
        Comment comment = commentService.updateComment(request.getCommentId(), token, request.getContent());
        if(comment == null){
            return null;
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CommentDTO.ResComment(comment));
    }

    @GetMapping("/comment/article/{id}")
    public ResponseEntity<List<CommentDTO.ResComment>> articleComments(@PathVariable("id") Long articleId){
        return ResponseEntity.status(HttpStatus.OK).body(
                commentService.articleToComments(articleId)
                        .stream()
                        .map(CommentDTO.ResComment::new)
                        .collect(Collectors.toList()));
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Void>deleteComment(@RequestHeader("Authorization") String token, @RequestBody CommentDTO.CommentDeleteReq request){
        jwtUtility.validateToken(token);
        commentService.deleteComment(request.getCommentId(), token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
