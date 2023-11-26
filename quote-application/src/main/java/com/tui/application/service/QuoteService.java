package com.tui.application.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tui.domain.model.Quote;

public interface QuoteService {
	
	
	 public Optional<Quote> getQuoteById(String id);
	 
	 public List<Quote> getQuoteByAuthor(String author);
	 
	 public  Map<String, Object> getAllQuotes(Integer page, Integer size );
	 
	
}
