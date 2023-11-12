package backend.projects.challengetui.service;


import backend.projects.challengetui.entity.Quote;
import backend.projects.challengetui.interfaces.QuotesApiResponse;
import backend.projects.challengetui.repository.QuoteRepository;
import backend.projects.challengetui.service.impl.QuoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private QuoteService quoteService = new QuoteServiceImpl(quoteRepository, restTemplate);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenQuoteExists_whenGetQuoteById_thenCorrectQuoteReturned() {
        // Given
        Quote expectedQuote = new Quote("1", "Test Quote", "Test Author", "Test Genre", 1);
        when(quoteRepository.findById("1")).thenReturn(Optional.of(expectedQuote));

        // When
        Quote actualQuote = quoteService.getQuoteById("1");

        // Then
        assertEquals(expectedQuote, actualQuote);
    }

    @Test
    public void givenQuotesExists_whenGetQuotesByAuthor_thenCorrectQuoteListReturned() {
        // Given
        String author = "Test Author";
        List<Quote> expectedQuotes = new ArrayList<>();
        expectedQuotes.add(new Quote("1", "Quote 1", author, "Genre A", 1));
        expectedQuotes.add(new Quote("2", "Quote 2", author, "Genre B", 2));
        when(quoteRepository.findByQuoteAuthor(author)).thenReturn(expectedQuotes);

        // When
        List<Quote> actualQuotes = quoteService.getQuotesByAuthor(author);

        // Then
        assertEquals(expectedQuotes, actualQuotes);
    }

    @Test
    public void givenQuotesExists_whenGetAllQuotes_thenCorrectQuoteListReturned() {
        // Given
        List<Quote> expectedQuotes = new ArrayList<>();
        expectedQuotes.add(new Quote("1", "Quote 1", "Author A", "Genre A", 1));
        expectedQuotes.add(new Quote("2", "Quote 2", "Author B", "Genre B", 2));
        when(quoteRepository.findAll()).thenReturn(expectedQuotes);

        // When
        List<Quote> actualQuotes = quoteService.getAllQuotes();

        // Then
        assertEquals(expectedQuotes, actualQuotes);
    }
}