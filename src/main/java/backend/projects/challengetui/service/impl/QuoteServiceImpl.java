package backend.projects.challengetui.service.impl;

import backend.projects.challengetui.entity.Quote;
import backend.projects.challengetui.interfaces.QuotesApiResponse;
import backend.projects.challengetui.repository.QuoteRepository;
import backend.projects.challengetui.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Value("${api.url}")
    private String apiUrl;

    private QuoteRepository quoteRepository;

    private RestTemplate restTemplate;

    @Autowired
    public QuoteServiceImpl( QuoteRepository quoteRepository, RestTemplate restTemplate) {
        this.quoteRepository = quoteRepository;
        this.restTemplate = restTemplate;
    }
    @Override
    public String loadData(int totalElementsToFetch) {
        String url;
        int leftElementsToFetch = totalElementsToFetch;
        List<Quote> quotes = new ArrayList<Quote>();

        for (int currentPage = 1; leftElementsToFetch > 0; currentPage++ ) {
            url = apiUrl + "/api/v3/quotes?page=" + currentPage;
            QuotesApiResponse response = restTemplate.getForObject(url, QuotesApiResponse.class);

            if (response != null && response.getData() != null) {
                quotes.addAll(response.getData());
                leftElementsToFetch -= 10;
                System.out.println("Elements left to add: " + leftElementsToFetch);
            }
        }
        quoteRepository.saveAll(quotes);
        return quotes.size() + " elements saved";
    }

    @Override
    public Quote getQuoteById(String id) {
        return this.quoteRepository.findById(id).get();
    }

    @Override
    public List<Quote> getQuotesByAuthor(String author) {
        return this.quoteRepository.findByQuoteAuthor(author);
    }

    @Override
    public List<Quote> getAllQuotes() {
        return this.quoteRepository.findAll();
    }
}
