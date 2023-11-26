package com.tui.application.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.tui.application.service.QuoteService;
import com.tui.application.usecase.GetQuoteUseCase;
import com.tui.domain.model.Quote;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetQuoteUseCaseImpl implements GetQuoteUseCase {
	
	private final QuoteService quoteService;
	

	@Override
	public Optional<Quote> getQuoteById(String id) {

		return quoteService.getQuoteById(id);
	}


	@Override
	public List<Quote> getQuoteByAuthor(String author) {

		return quoteService.getQuoteByAuthor(author);
	}

}
