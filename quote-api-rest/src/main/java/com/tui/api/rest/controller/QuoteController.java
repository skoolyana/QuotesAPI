package com.tui.api.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.tui.api.QuotesApi;
import com.tui.api.rest.mapper.QuoteMapper;
import com.tui.application.usecase.GetQuoteUseCase;
import com.tui.model.Quote;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class QuoteController implements QuotesApi {
	
	
	private final GetQuoteUseCase getQuoteUseCase;
	
	private final QuoteMapper quoteMapper;

	
	@Override
	public ResponseEntity<List<Quote>> getAllQuotes() {
		// TODO Auto-generated method stub
		return QuotesApi.super.getAllQuotes();
	}

	@Override
	public ResponseEntity<Quote> getQuoteById(String id) {
				
		Optional<com.tui.domain.model.Quote> quoteDomain =getQuoteUseCase.getQuoteById(id);
			
		return quoteDomain.map(quote -> ResponseEntity.ok(quoteMapper.mapToApiModel(quote)))
                .orElseGet(() -> ResponseEntity.ok().build());

	}

	@Override
	public ResponseEntity<List<Quote>> getQuotesByAuthor(String author) {
		
		List<com.tui.domain.model.Quote> quoteDomain =getQuoteUseCase.getQuoteByAuthor(author);

		 List<Quote> quotes = quoteDomain.stream()
		            .map(quoteMapper::mapToApiModel)
		            .collect(Collectors.toList());

		    return ResponseEntity.ok(quotes);
		}
	}


