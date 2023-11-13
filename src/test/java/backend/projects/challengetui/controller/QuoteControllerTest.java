package backend.projects.challengetui.controller;

import backend.projects.challengetui.entity.Quote;
import backend.projects.challengetui.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QuoteControllerTest {

    @Mock
    private QuoteService quoteService;

    @InjectMocks
    private QuoteController quoteController = new QuoteController(quoteService);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenAuthorExists_whenGetQuotesByAuthor_thenReturnsQuotes() {
        // Given
        String author = "TestAuthor";
        List<Quote> quotes = Collections.singletonList(new Quote("1", "Test Quote", author, "Test Genre", 0));
        when(quoteService.getQuotesByAuthor(author)).thenReturn(quotes);

        // When
        ResponseEntity<List<Quote>> response = quoteController.getQuotesByAuthor(author);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(quotes, response.getBody());
        verify(quoteService, times(1)).getQuotesByAuthor(author);
    }

    @Test
    public void givenQuoteExists_whenGetQuotesById_thenReturnsQuote() {
        // Given
        String quoteId = "1";
        Quote quote = new Quote(quoteId, "Test Quote", "Test Author", "Test Genre", 0);
        when(quoteService.getQuoteById(quoteId)).thenReturn(quote);

        // When
        ResponseEntity<Quote> response = quoteController.getQuotesById(quoteId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(quote, response.getBody());
        verify(quoteService, times(1)).getQuoteById(quoteId);
    }

    @Test
    public void givenQuotesExist_whenGetAllQuotes_thenReturnsQuotesList() {
        // Given
        List<Quote> expectedQuotes = Arrays.asList(
                new Quote("1", "Quote 1", "Author 1", "Genre 1", 0),
                new Quote("2", "Quote 2", "Author 2", "Genre 2", 0)
        );
        when(quoteService.getAllQuotes()).thenReturn(expectedQuotes);

        // When
        ResponseEntity<List<Quote>> response = quoteController.getAllQuotes();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedQuotes, response.getBody());
        verify(quoteService, times(1)).getAllQuotes();
    }

}
