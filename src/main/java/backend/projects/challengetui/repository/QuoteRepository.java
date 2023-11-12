package backend.projects.challengetui.repository;

import backend.projects.challengetui.entity.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuoteRepository extends MongoRepository<Quote, String> {

    List<Quote> findByQuoteAuthor(String author);

}
