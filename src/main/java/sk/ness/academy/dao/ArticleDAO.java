package sk.ness.academy.dao;

import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.Article2;

import java.util.List;

public interface ArticleDAO {

	  /** Returns {@link Article} with provided ID */
	  Article findByID(Integer articleId);

	  /** Returns all available {@link Article}s */
	  List<Article2> findAll();

	  /** Persists {@link Article} into the DB */
	  void persist(Article article);

	  /** Delete {@link Article} with provided ID */
	  void deleteByID(Integer articleId);

	  /** Returns {@link Article}s with provided text */
	  List<Article2> searchArticles(String text);
	}
