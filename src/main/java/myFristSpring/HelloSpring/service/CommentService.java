package myFristSpring.HelloSpring.service;

import lombok.RequiredArgsConstructor;
import myFristSpring.HelloSpring.domain.Article;
import myFristSpring.HelloSpring.domain.Comment;
import myFristSpring.HelloSpring.domain.Member;
import myFristSpring.HelloSpring.exception.InvalidArticleIdException;
import myFristSpring.HelloSpring.exception.InvalidComIdMemException;
import myFristSpring.HelloSpring.exception.InvalidCommentIdException;
import myFristSpring.HelloSpring.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final MemberService memberService;
    private final ArticleService articleService;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment saveComment(String token, Long articleId, String content) {
        Member member = memberService.tokenToMember(token);
        Article article = articleService.findArticle(articleId);
        if (article == null) {
            throw new InvalidArticleIdException();
        }
        Comment comment = new Comment(member, article, content);
        commentRepository.addComment(comment);
        return comment;
    }

    @Transactional
    public Comment updateComment(Long commentId, String token, String content) {
        Comment comment = commentRepository.findById(commentId);
        if (comment == null) {
            throw new InvalidCommentIdException();
        }
        Member member = memberService.tokenToMember(token);
        if (comment.getWriter() != (member)) {
            throw new InvalidComIdMemException();
        }
        comment.updateComment(content);
        return comment;

    }

    public List<Comment> articleToComments(Long articleId) {
        Article article=articleService.findArticle(articleId);
        return commentRepository.findArticleComment(article);
    }

    @Transactional
    public void deleteComment(Long commentId, String token) {
        Comment comment = commentRepository.findById(commentId);
        Member member = memberService.tokenToMember(token);
        if (comment.getWriter().equals(member)) {
            commentRepository.deleteComment(comment);
        }
    }
}