package com.tui.application.usecase.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


	@Override
	public Map<String,Object> getAllQuotes(Integer page, Integer size) {

		return quoteService.getAllQuotes(page, size);
	}

}
