package com.tui.repository;

import com.tui.repository.entity.QuoteEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MongoQuoteRepository extends MongoRepository<QuoteEntity, String> {
	
		@Override	
	    Optional<QuoteEntity> findById(String id);
	 	
		
	    List<QuoteEntity> findByQuoteAuthor(String author);
	    
	    
	    @Override	
	    Page<QuoteEntity> findAll(Pageable pageable);
	    
	 		 	
}

