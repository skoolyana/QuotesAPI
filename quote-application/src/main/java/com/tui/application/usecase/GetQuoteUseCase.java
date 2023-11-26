package com.tui.application.usecase;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tui.domain.model.Quote;

public interface GetQuoteUseCase {
	
	Optional<Quote> getQuoteById(String id);
	
	List<Quote> getQuoteByAuthor(String author);
	
	public Map<String, Object> getAllQuotes(Integer page, Integer size);
}
