package com.tui.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.tui.domain.model.Quote;
import com.tui.domain.repository.QuoteRepository;
import com.tui.repository.entity.QuoteEntity;
import com.tui.repository.mapper.QuoteEntityMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class QuoteRepositoryImpl implements QuoteRepository {

	private final MongoQuoteRepository mongoQuoteRepository;
    private final QuoteEntityMapper quoteEntityMapper;
	
	
    @Override
    public List<Quote> findByAuthor(String author) {
        List<QuoteEntity> entities = mongoQuoteRepository.findByQuoteAuthor(author);
        return entities.stream()
                .map(quoteEntityMapper::mapToDomainModel)
                .collect(Collectors.toList());
    }

	@Override
	public Optional<Quote> findById(String id) {

		Optional<QuoteEntity> optionalEntity = mongoQuoteRepository.findById(id);
		Optional<Quote> optionalQuote = optionalEntity.map(entity -> quoteEntityMapper.mapToDomainModel(entity));
		return optionalQuote;
	}

	@Override
	public Map<String,Object> getAllQuotes(Integer page, Integer size) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		
		Pageable pageable = PageRequest.of(page, size);

	    Page<QuoteEntity> quoteEntityPage = mongoQuoteRepository.findAll(pageable);
	    
		List<Quote> result =  quoteEntityPage.stream().map(quoteEntityMapper::mapToDomainModel)
	            .collect(Collectors.toList());
		
		resultMap.put("result", result);
		resultMap.put("totalElements", quoteEntityPage.getTotalElements());
		resultMap.put("pages", quoteEntityPage.getTotalPages());
		
		return resultMap;
		
	}	
}
