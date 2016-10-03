package be.pxl.spring.rest.fallout;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping(value = MemorableQuotesController.QUOTE_BASE_URL)
@RestController
public class MemorableQuotesController {

    public static final String QUOTE_BASE_URL = "/quote";
    private static final List<Quote> quotes;

    static {
        quotes = new ArrayList<>();
        quotes.add(new Quote("Narrator", "War...War never changes"));
        quotes.add(new Quote("Mr. Fantastic", "They asked me how well I understood theoretical physics. I said I had a theoretical degree in physics. They said welcome aboard"));
        quotes.add(new Quote("Liberty Prime","Freedom is the sovereign right of every American"));
        quotes.add(new Quote("Thomas Hildern","Too many people have opnions on things they know nothing about. And the more ignorant they are, the more opinions they have"));
        quotes.add(new Quote("Moira Brown", "Here, take a few radiation chems, as my little way of saying, \"I'm sorry I twisted your DNA like a kitten with a ball of yarn.\""));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Quote> index(@RequestParam("author") String author) {
        return quotes.stream().filter(s -> Objects.equals(s.getAuthor(), author)).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Quote insertQoute(@RequestBody Quote quote) {
        quotes.add(quote);
        return quote;
    }
}
