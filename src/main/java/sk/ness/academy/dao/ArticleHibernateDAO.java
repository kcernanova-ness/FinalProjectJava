package sk.ness.academy.dao;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.Article2;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class ArticleHibernateDAO implements ArticleDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public Article findByID(final Integer articleId) {
    try{
      Article art = this.sessionFactory.getCurrentSession().get(Article.class, articleId);
      Hibernate.initialize(art.getComments());
      return art;
    }catch(NullPointerException e){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Article2> findAll() { return this.sessionFactory.getCurrentSession().createQuery("select new sk.ness.academy.dto.Article2( id, title, text, author, createTimestamp) from Article", Article2.class).list(); }

  @Override
  public void persist(final Article article) { this.sessionFactory.getCurrentSession().saveOrUpdate(article); }

  @Override
  public void deleteByID(Integer articleId) {
    try{
        Article article = this.sessionFactory.getCurrentSession().load(Article.class, articleId);
        this.sessionFactory.getCurrentSession().delete(article);
        this.sessionFactory.getCurrentSession().flush();
    }catch(EntityNotFoundException e){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public List<Article2> searchArticles(String text) { return this.sessionFactory.getCurrentSession().createQuery("select new sk.ness.academy.dto.Article2( id, title, text, author, createTimestamp) from Article where author like '%"+text+"%' OR title like '%"+text+"%' OR text like '%"+text+"%'", Article2.class).list(); }

}
