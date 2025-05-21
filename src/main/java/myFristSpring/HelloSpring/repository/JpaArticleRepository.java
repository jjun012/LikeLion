package myFristSpring.HelloSpring.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import myFristSpring.HelloSpring.domain.Article;
import myFristSpring.HelloSpring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JpaArticleRepository implements ArticleRepository{
    private final EntityManager em;
    private final MemberRepository memberRepository;

    public JpaArticleRepository(EntityManager em, MemberRepository memberRepository) {
        this.em = em;
        this.memberRepository = memberRepository;
    }

    @Override
    public Article saveArticle(Article article) {
        em.persist(article);
        return article;
    }

    @Override
    public void deleteArticle(Article article) {
        em.remove(article);
    }

    @Override
    public Article findById(Long articleId) {
        return em.find(Article.class,articleId);
    }

    @Override
    public List<Article> findAll() { return em.createQuery("select a from Article a",Article.class).getResultList(); }

    @Override
    public List<Article> findUserAll(Long memberId){
        Member member = memberRepository.findById(memberId);
        return em.createQuery("select a from Article a where a.writer=:member",Article.class)
                .setParameter("member",member).getResultList();
    }
}
