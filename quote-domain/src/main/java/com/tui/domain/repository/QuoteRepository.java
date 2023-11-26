package com.tui.domain.repository;

import java.util.List;
import java.util.Optional;

import com.tui.domain.model.Quote;

public interface QuoteRepository{
  
	 List<Quote> findByAuthor(String author);
	 
	 Optional<Quote> findById(String id);
	 
}
