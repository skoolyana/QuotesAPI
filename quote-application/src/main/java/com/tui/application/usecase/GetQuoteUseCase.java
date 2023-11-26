package com.tui.application.usecase;

import java.util.Optional;

import com.tui.domain.model.Quote;

public interface GetQuoteUseCase {
	
	Optional<Quote> getQuoteById(String id);	
}
