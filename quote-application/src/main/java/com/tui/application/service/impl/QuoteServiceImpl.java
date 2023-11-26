package com.tui.application.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tui.application.service.QuoteService;
import com.tui.domain.model.Quote;
import com.tui.domain.repository.QuoteRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
	
	
	private final QuoteRepository quoteRepository;


	@Override
	public Optional<Quote> getQuoteById(String id) {
		
		return  quoteRepository.findById(id);
	}
	
}
