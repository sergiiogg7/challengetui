package backend.projects.challengetui.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quote")
public class Quote {

    @Id
    private String id;

    private String quoteText;
    private String quoteAuthor;
    private String quoteGenre;
    private int __v;

    public Quote() {
    }

    public Quote(String id, String quoteText, String quoteAuthor, String quoteGenre, int __v) {
        this.id = id;
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
        this.quoteGenre = quoteGenre;
        this.__v = __v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }

    public String getQuoteGenre() {
        return quoteGenre;
    }

    public void setQuoteGenre(String quoteGenre) {
        this.quoteGenre = quoteGenre;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}