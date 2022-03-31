package sk.ness.academy.service;

import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.Article2;

import java.util.List;

public interface ArticleService {

	  /** Returns {@link Article} with provided ID */
	  Article findByID(Integer articleId);

	  /** Returns all available {@link Article}s */
	  List<Article2> findAll();

	  /** Creates new {@link Article} */
	  void createArticle(Article article);

	  /** Creates new {@link Article}s by ingesting all articles from json */
	  void ingestArticles(String jsonArticles);

	  /** Delete {@link Article} with provided ID */
	  void deleteByID(Integer articleId);

	  /** Returns {@link Article}s with provided text */
	  List<Article2> searchArticles(String text);

	}
