package com.tui.application.service;

import java.util.Optional;

import com.tui.domain.model.Quote;

public interface QuoteService {
	
	
	 public Optional<Quote> getQuoteById(String id);
	
}
