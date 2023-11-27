package com.tui.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.tui.domain.model.Quote;
import com.tui.repository.entity.QuoteEntity;
import com.tui.repository.mapper.config.InfraMapStructConfig;

@Mapper(config = InfraMapStructConfig.class)
@Component
public interface QuoteEntityMapper {

	
	QuoteEntityMapper INSTANCE = Mappers.getMapper(QuoteEntityMapper.class);

    // Automatically maps fields with identical names
    Quote mapToDomainModel(QuoteEntity quoteEntity);

    // Automatically maps fields with identical names
    QuoteEntity mapToEntity(Quote quote);
		
}

