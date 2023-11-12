package backend.projects.challengetui.interfaces;

import backend.projects.challengetui.entity.Quote;

import java.util.List;

public class QuotesApiResponse {

    private List<Quote> data;

    public List<Quote> getData() {
        return data;
    }

    public void setData(List<Quote> data) {
        this.data = data;
    }

}
