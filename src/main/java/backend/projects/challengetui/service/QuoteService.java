package backend.projects.challengetui.service;

import backend.projects.challengetui.entity.Quote;

import java.util.List;

public interface QuoteService{

    String loadData(int totalElementsToFetch);
    Quote getQuoteById(String id);
    List<Quote> getQuotesByAuthor(String author);
    List<Quote> getAllQuotes();

}
