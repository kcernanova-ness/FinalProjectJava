package sk.ness.academy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.Article2;
import sk.ness.academy.dto.Author;
import sk.ness.academy.dto.AuthorStats;
import sk.ness.academy.service.ArticleService;
import sk.ness.academy.service.AuthorService;
import sk.ness.academy.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BlogController {

  @Resource
  private ArticleService articleService;

  @Resource
  private CommentService commentService;

  @Resource
  private AuthorService authorService;

  // ~~ Article
  @RequestMapping(value = "articles", method = RequestMethod.GET)
  public List<Article2> getAllArticles() { return this.articleService.findAll(); }

  @RequestMapping(value = "articles/{articleId}", method = RequestMethod.GET)
  public Article getArticle(@PathVariable final Integer articleId) {
	  return this.articleService.findByID(articleId);
  }

  @RequestMapping(value = "articles/{articleId}", method = RequestMethod.DELETE)
  public void deleteArticle(@PathVariable final Integer articleId) { this.articleService.deleteByID(articleId); }

  @RequestMapping(value = "articles/search/{searchText}", method = RequestMethod.GET)
  public List<Article2> searchArticle(@PathVariable final String searchText) { return this.articleService.searchArticles(searchText); }

  @RequestMapping(value = "articles", method = RequestMethod.PUT)
  public void addArticle(@RequestBody final Article article) { this.articleService.createArticle(article); }

  // ~~ Author
  @RequestMapping(value = "authors", method = RequestMethod.GET)
  public List<Author> getAllAuthors() { return this.authorService.findAll(); }

  @RequestMapping(value = "authors/stats", method = RequestMethod.GET)
  public List<AuthorStats> authorStats() { return this.authorService.findAllWithNumArt(); }

  // ~~ Comments
  @RequestMapping(value = "comments", method = RequestMethod.PUT)
  public void addComment(@RequestBody final Comment com) {
      Article art = getArticle(com.getArticle_id());
      if(art == null){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
      this.commentService.createComment(com);
  }

  @RequestMapping(value = "comments", method = RequestMethod.GET)
  public List<Comment> getAllComments() { return this.commentService.findAll(); }

  @RequestMapping(value = "comments/{commentId}", method = RequestMethod.GET)
  public Comment getComment(@PathVariable final Integer commentId) { return this.commentService.findByID(commentId); }

  @RequestMapping(value = "comments/{commentId}", method = RequestMethod.DELETE)
  public void deleteComment(@PathVariable final Integer commentId) { this.commentService.deleteByID(commentId); }

}
