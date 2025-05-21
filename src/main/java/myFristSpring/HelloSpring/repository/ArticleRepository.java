package myFristSpring.HelloSpring.repository;

import myFristSpring.HelloSpring.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ArticleRepository {
    Article saveArticle(Article article);
    void deleteArticle(Article article);
    Article findById(Long articleId);
    List<Article> findAll();
    List<Article> findUserAll(Long memberId);


}
