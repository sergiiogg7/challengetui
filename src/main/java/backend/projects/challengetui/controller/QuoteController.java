package backend.projects.challengetui.controller;

import backend.projects.challengetui.entity.Quote;
import backend.projects.challengetui.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quotesService) {
        this.quoteService = quotesService;
    }

    @PostMapping("/elements/{items}")
    public ResponseEntity<String> loadData(@PathVariable int items) {
        return new ResponseEntity<String>(this.quoteService.loadData(items), HttpStatus.CREATED);
    }

    @GetMapping("/authors/{author}")
    public ResponseEntity<List<Quote>> getQuotesByAuthor(@PathVariable String author) {
        return new ResponseEntity<List<Quote>>(this.quoteService.getQuotesByAuthor(author), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuotesById(@PathVariable String id) {
        return new ResponseEntity<Quote>(this.quoteService.getQuoteById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        return new ResponseEntity<List<Quote>>(this.quoteService.getAllQuotes(), HttpStatus.OK);
    }

}
