package com.tui.application.service;

import java.util.List;
import java.util.Optional;

import com.tui.domain.model.Quote;

public interface QuoteService {
	
	
	 public Optional<Quote> getQuoteById(String id);
	 
	 public List<Quote> getQuoteByAuthor(String author);
	 
	
}
