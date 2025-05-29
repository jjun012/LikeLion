package myFristSpring.HelloSpring.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import myFristSpring.HelloSpring.domain.Article;
import myFristSpring.HelloSpring.domain.Comment;
import myFristSpring.HelloSpring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final EntityManager em;

    @Override
    public Comment addComment(Comment comment) {
        this.em.persist(comment);
        return comment;
    }

    @Override
    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public void deleteComment(Comment comment) {
        em.remove(comment);
    }

    @Override
    public List<Comment> findArticleComment(Article article) {
        return em.createQuery("select c from Comment c where c.article=: article",Comment.class)
                .setParameter("article", article)
                .getResultList();
    }

    @Override
    public List<Comment> findMemberComment(Member member) {
        return em.createQuery("select c from Comment c where c.writer=:member",Comment.class)
                .setParameter("member", member)
                .getResultList();
    }
}
