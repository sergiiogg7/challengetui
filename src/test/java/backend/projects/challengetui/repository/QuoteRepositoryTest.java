package backend.projects.challengetui.repository;


import backend.projects.challengetui.entity.Quote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class QuoteRepositoryTest {

    @Autowired
    private QuoteRepository quoteRepository;

    @Test
    public void givenQuotesExist_whenFindByQuoteAuthor_thenReturnMatchingQuotes() {
        // Given
        Quote quote1 = new Quote("1", "Quote 1", "Author A", "Genre A", 1);
        Quote quote2 = new Quote("2", "Quote 2", "Author A", "Genre B", 2);
        Quote quote3 = new Quote("3", "Quote 3", "Author B", "Genre C", 3);

        quoteRepository.saveAll(List.of(quote1, quote2, quote3));

        // When
        List<Quote> quotesByAuthorA = quoteRepository.findByQuoteAuthor("Author A");

        //Then
        assertTrue(quotesByAuthorA.size() == 2, "Quote 2 should be in the result");
        assertFalse(quotesByAuthorA.contains(quote3), "Quote 3 should not be in the result");
    }

    @Test
    public void givenQuoteExists_whenFindById_thenReturnQuote() {
        // Given
        Quote quote = new Quote("1", "Test Quote", "Test Author", "Test Genre", 1);
        quoteRepository.save(quote);

        // When
        Quote foundQuote = quoteRepository.findById("1").orElse(null);

        // Then
        assertNotNull(foundQuote, "Quote should not be null");
        assertEquals("Test Quote", foundQuote.getQuoteText(), "Quote text should match");
        assertEquals("Test Author", foundQuote.getQuoteAuthor(), "Quote author should match");
        assertEquals("Test Genre", foundQuote.getQuoteGenre(), "Quote genre should match");
        assertEquals(1, foundQuote.get__v(), "__v should match");
    }

    @Test
    public void givenNonexistentId_whenFindById_thenReturnEmptyOptional() {
        // Given an ID that doesn't exist in the repository

        // When
        Optional<Quote> foundQuote = quoteRepository.findById("nonexistentId");

        // Then
        assertTrue(foundQuote.isEmpty(), "Optional should be empty for nonexistent ID");
    }

    @Test
    public void givenQuotesList_whenSaveAll_thenQuotesShouldBeSaved() {
        // Given
        Quote quote1 = new Quote("1", "Quote 1", "Author A", "Genre A", 1);
        Quote quote2 = new Quote("2", "Quote 2", "Author B", "Genre B", 2);
        List<Quote> quotesToSave = List.of(quote1, quote2);

        // When
        List<Quote> savedQuotes = quoteRepository.saveAll(quotesToSave);

        // Then
        assertEquals(2, savedQuotes.size(), "Two quotes should be saved");

        assertTrue(savedQuotes.contains(quote1), "Saved quotes should contain Quote 1");
        assertTrue(savedQuotes.contains(quote2), "Saved quotes should contain Quote 2");
    }

}
