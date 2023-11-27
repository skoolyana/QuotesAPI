package com.tui.api.rest.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.tui.api.rest.mapper.QuoteMapper;
import com.tui.application.usecase.GetQuoteUseCase;
import com.tui.model.Quote;

@DirtiesContext
@WebMvcTest(controllers = QuoteController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {QuoteController.class})

public class QuoteControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetQuoteUseCase getQuoteUseCase;

    @MockBean
    private QuoteMapper quoteMapper;

    
    @Test
    public void testGetQuoteById() throws Exception {
       
    	when(getQuoteUseCase.getQuoteById("5eb17aaeb69dc744b4e73586"))
        .thenReturn(Optional.of(new com.tui.domain.model.Quote().builder()
                .id("5eb17aaeb69dc744b4e73586")
                .quoteText("The first rule of any technology...")
                .quoteAuthor("Bill Gates")
                .quoteGenre("business")
                .build()));

	    Quote responseModel = new Quote();
	    responseModel.setId("5eb17aaeb69dc744b4e73586");
	    responseModel.setQuoteText("The first rule of any technology...");
	    responseModel.setQuoteAuthor("Bill Gates");
	    responseModel.setQuoteGenre("business");
    	

        when(quoteMapper.mapToApiModel(Mockito.any(com.tui.domain.model.Quote.class)))
            .thenReturn(responseModel);  
        
        mockMvc.perform(get("/api/v1/quotes/5eb17aaeb69dc744b4e73586"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$._id").value("5eb17aaeb69dc744b4e73586"))
            .andExpect(jsonPath("$.quoteText").value("The first rule of any technology..."))
            .andExpect(jsonPath("$.quoteAuthor").value("Bill Gates"))
            .andExpect(jsonPath("$.quoteGenre").value("business"));

    }
    
    
    @Test
    public void testGetQuotesByAuthor() throws Exception {
        // Mock data for the getQuotesByAuthor method
        String author = "age";

        // Sample quotes
        com.tui.domain.model.Quote quote1 = new com.tui.domain.model.Quote();
        quote1.setId("5eb17aadb69dc744b4e70e24");
        quote1.setQuoteText("Old age is when the liver spots show through your gloves.");
        quote1.setQuoteAuthor(author);

        com.tui.domain.model.Quote quote2 = new com.tui.domain.model.Quote();
        quote2.setId("5eb17aadb69dc744b4e70e8a");
        quote2.setQuoteText("I'm at the age now where just putting my cigar in its holder is a thrill.");
        quote2.setQuoteAuthor(author);
        
        List<com.tui.domain.model.Quote> quoteDomainList = Arrays.asList(quote1, quote2);

        // Mock the behavior of getQuoteUseCase.getQuoteByAuthor
        when(getQuoteUseCase.getQuoteByAuthor(anyString())).thenReturn(quoteDomainList);

        // Mock the mapping of domain model to API model
        Quote apiQuote1 = new Quote();
        apiQuote1.setId("5eb17aadb69dc744b4e70e24");
        apiQuote1.setQuoteText("Old age is when the liver spots show through your gloves.");
        apiQuote1.setQuoteAuthor(author);

        Quote apiQuote2 = new Quote();
        apiQuote2.setId("5eb17aadb69dc744b4e70e8a");
        apiQuote2.setQuoteText("I'm at the age now where just putting my cigar in its holder is a thrill.");
        apiQuote2.setQuoteAuthor(author);

        when(quoteMapper.mapToApiModel(quote1)).thenReturn(apiQuote1);
        when(quoteMapper.mapToApiModel(quote2)).thenReturn(apiQuote2);
        
        
        // Perform the GET request
        mockMvc.perform(get("/api/v1/quotes/author/{author}", author))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]._id").value("5eb17aadb69dc744b4e70e24"))
                .andExpect(jsonPath("$[0].quoteText").value("Old age is when the liver spots show through your gloves."))
                .andExpect(jsonPath("$[0].quoteAuthor").value(author))
                .andExpect(jsonPath("$[1]._id").value("5eb17aadb69dc744b4e70e8a"))
                .andExpect(jsonPath("$[1].quoteText").value("I'm at the age now where just putting my cigar in its holder is a thrill."))
                .andExpect(jsonPath("$[1].quoteAuthor").value(author));
    }
    
    @Test
    public void testGetAllQuotes() throws Exception {
        // Mock data for the getAllQuotes method
        int page = 1;
        int size = 10;

        // Sample quotes
        com.tui.domain.model.Quote quote1 = new com.tui.domain.model.Quote();
        quote1.setId("5eb17aadb69dc744b4e70e24");
        quote1.setQuoteText("Old age is when the liver spots show through your gloves.");
        quote1.setQuoteAuthor("Phyllis Diller");

        com.tui.domain.model.Quote quote2 = new com.tui.domain.model.Quote();
        quote2.setId("5eb17aadb69dc744b4e70e8a");
        quote2.setQuoteText("I'm at the age now where just putting my cigar in its holder is a thrill.");
        quote2.setQuoteAuthor("George Burns");

       
        List<com.tui.domain.model.Quote> quoteDomainList = Arrays.asList(quote1, quote2);

        // Mock the behavior of getQuoteUseCase.getAllQuotes
        CompletableFuture<Map<String, Object>> quotesFuture = CompletableFuture.supplyAsync(() -> {
            Map<String, Object> quotesMap = new HashMap<>();
            quotesMap.put("result", quoteDomainList);
            quotesMap.put("totalElements", (long) quoteDomainList.size());
            quotesMap.put("pages", 1);
            return quotesMap;
        });

        when(getQuoteUseCase.getAllQuotes(anyInt(), anyInt())).thenReturn(quotesFuture.get());


        // Mock the mapping of domain model to API model
        Quote apiQuote1 = new Quote();
        apiQuote1.setId("5eb17aadb69dc744b4e70e24");
        apiQuote1.setQuoteText("Old age is when the liver spots show through your gloves.");
        apiQuote1.setQuoteAuthor("Phyllis Diller");

        Quote apiQuote2 = new Quote();
        apiQuote2.setId("5eb17aadb69dc744b4e70e8a");
        apiQuote2.setQuoteText("I'm at the age now where just putting my cigar in its holder is a thrill.");
        apiQuote2.setQuoteAuthor("George Burns");

        when(quoteMapper.mapToApiModel(quote1)).thenReturn(apiQuote1);
        when(quoteMapper.mapToApiModel(quote2)).thenReturn(apiQuote2);

        // Perform the GET request
        mockMvc.perform(get("/api/v1/quotes")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0]._id").value("5eb17aadb69dc744b4e70e24"))
                .andExpect(jsonPath("$.content[0].quoteText").value("Old age is when the liver spots show through your gloves."))
                .andExpect(jsonPath("$.content[0].quoteAuthor").value("Phyllis Diller"))
                .andExpect(jsonPath("$.content[1]._id").value("5eb17aadb69dc744b4e70e8a"))
                .andExpect(jsonPath("$.content[1].quoteText").value("I'm at the age now where just putting my cigar in its holder is a thrill."))
                .andExpect(jsonPath("$.content[1].quoteAuthor").value("George Burns"))
                .andExpect(jsonPath("$.totalElements").value(quoteDomainList.size()))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.pageable.offset").value(page * size))
                .andExpect(jsonPath("$.pageable.pageNumber").value(page))
                .andExpect(jsonPath("$.pageable.pageSize").value(size))
                .andExpect(jsonPath("$.pageable.paged").value(true))
                .andExpect(jsonPath("$.pageable.unpaged").value(false));
    }
}
