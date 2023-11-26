package com.tui.application.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


	@Override
	public List<Quote> getQuoteByAuthor(String author) {

		return  quoteRepository.findByAuthor(author);
	}


	@Override
	public Map<String,Object> getAllQuotes(Integer page, Integer size ) {
		
		return  quoteRepository.getAllQuotes(page,size);
	}
	
}
