package com.tui.application.usecase.impl;


import com.tui.application.service.QuoteService;
import com.tui.application.usecase.impl.GetQuoteUseCaseImpl;
import com.tui.domain.model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetQuoteUseCaseImplTest {

    @Mock
    private QuoteService quoteService;

    @InjectMocks
    private GetQuoteUseCaseImpl getQuoteUseCase;

    private List<Quote> sampleQuotes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize sample data
        sampleQuotes = List.of(
                new Quote("5eb17aadb69dc744b4e70e48", "The philosophies of one age have become the absurdities of the next...",
                        "William Osler", "age", 0),
                new Quote("5eb17aadb69dc744b4e70d92", "Reading, after a certain age, diverts the mind too much from its creative pursuits...",
                        "Albert Einstein", "age", 0),
                new Quote("5eb17aadb69dc744b4e70f70", "I think I'm a bit less inhibited, and not thinking too much before speaking...",
                        "Kristen Stewart", "age", 0)
        );
    }

    @Test
    void testGetQuoteById() {
        String quoteId = "5eb17aadb69dc744b4e70e48";
        Quote expectedQuote = sampleQuotes.get(0);

        when(quoteService.getQuoteById(quoteId)).thenReturn(Optional.of(expectedQuote));

        Optional<Quote> result = getQuoteUseCase.getQuoteById(quoteId);

        verify(quoteService, times(1)).getQuoteById(quoteId);
        assertEquals(Optional.of(expectedQuote), result);
    }

    @Test
    void testGetQuoteByAuthor() {
        String author = "William Osler";

        when(quoteService.getQuoteByAuthor(author)).thenReturn(List.of(sampleQuotes.get(0)));

        List<Quote> result = getQuoteUseCase.getQuoteByAuthor(author);

        verify(quoteService, times(1)).getQuoteByAuthor(author);
        assertEquals(List.of(sampleQuotes.get(0)), result);
    }

    @Test
    void testGetAllQuotes() {
        Integer page = 1;
        Integer size = 10;
        Map<String, Object> expectedQuotes = Map.of("quotes", sampleQuotes, "total", sampleQuotes.size());

        when(quoteService.getAllQuotes(page, size)).thenReturn(expectedQuotes);

        Map<String, Object> result = getQuoteUseCase.getAllQuotes(page, size);

        verify(quoteService, times(1)).getAllQuotes(page, size);
        assertEquals(expectedQuotes, result);
    }
}

