package myFristSpring.HelloSpring.service;

import lombok.RequiredArgsConstructor;
import myFristSpring.HelloSpring.domain.Article;
import myFristSpring.HelloSpring.domain.Member;
import myFristSpring.HelloSpring.repository.ArticleRepository;
import myFristSpring.HelloSpring.repository.MemberRepository;
import myFristSpring.HelloSpring.security.JwtUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final JwtUtility jwtUtility;
    private final MemberService memberService;

    @Transactional
    public Article addArticle(String userId, String title, String content) {
        Member member = memberService.findByUserId(userId);
        Article article = new Article(title, content, member);
        return articleRepository.saveArticle(article);
    }

    @Transactional
    public Article updateArticle(Long articleId, String title, String content, String token){
        Article article=articleRepository.findById(articleId);
        Member member=memberService.tokenToMember(token);
        if(article.getWriter()==member){
            article.update(title,content);
        }
        return article;
    }
    @Transactional
    public void deleteArticle(Long articleId, String token){
        Article article=articleRepository.findById(articleId);
        Member member=memberService.findByUserId(token);
        if(article.getWriter()==member){
            articleRepository.deleteArticle(article);
        }
    }
    public Article findArticle(Long articleId){ return articleRepository.findById(articleId);}

    public List<Article> findAllArticles(){ return articleRepository.findAll();}

    public List<Article> findUserArticles(String memberId){
        Member member=memberService.findByUserId(memberId);
        return articleRepository.findUserAll(member.getId());
    }
}
