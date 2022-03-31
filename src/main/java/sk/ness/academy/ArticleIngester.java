package sk.ness.academy;

import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sk.ness.academy.config.DatabaseConfig;
import sk.ness.academy.domain.Article;
import sk.ness.academy.service.ArticleService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "sk.ness.academy", excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AssignmentApplication.class) })
@Import(DatabaseConfig.class)
public class ArticleIngester {

  public static void main(final String[] args) {
    try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ArticleIngester.class)) {
      context.registerShutdownHook();

      final ArticleService articleService = context.getBean(ArticleService.class);

      // Load file with articles and ingest

      InputStream res = new ClassPathResource("articles_to_ingest.txt", Article.class.getClassLoader()).getInputStream();
      BufferedReader reader = new BufferedReader(
              new InputStreamReader(res));
      String articles = reader.lines()
              .collect(Collectors.joining("\n"));

      articleService.ingestArticles(articles);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
