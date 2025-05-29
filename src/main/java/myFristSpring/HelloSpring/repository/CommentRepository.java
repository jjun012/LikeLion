package myFristSpring.HelloSpring.repository;

import myFristSpring.HelloSpring.domain.Article;
import myFristSpring.HelloSpring.domain.Comment;
import myFristSpring.HelloSpring.domain.Member;

import java.util.List;

public interface CommentRepository {
    Comment addComment(Comment comment);
    Comment findById(Long id);
    void deleteComment(Comment comment);
    List<Comment> findArticleComment(Article article);
    List<Comment> findMemberComment(Member member);
}
