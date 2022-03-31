package sk.ness.academy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import sk.ness.academy.dao.ArticleDAO;
import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.Article2;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

  @Resource
  private ArticleDAO articleDAO;

  @Override
  public Article findByID(final Integer articleId) {
	  return this.articleDAO.findByID(articleId);
  }

  @Override
  public List<Article2> findAll() {
	  return this.articleDAO.findAll();
  }

  @Override
  public void createArticle(final Article article) {
	  this.articleDAO.persist(article);
  }

  @Override
  public void ingestArticles(final String jsonArticles){
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Article[] data = objectMapper.readValue(jsonArticles, Article[].class);
      for (Article art: data) {
        this.articleDAO.persist(art);
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteByID(Integer articleId) {
      this.articleDAO.deleteByID(articleId);
  }

  @Override
  public List<Article2> searchArticles(String text) {
     return this.articleDAO.searchArticles(text);
  }

}
