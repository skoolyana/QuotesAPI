package com.tui.api.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.tui.api.rest.mapper.config.MapStructConfig;
import com.tui.model.Quote;

@Mapper(config = MapStructConfig.class)
public interface QuoteMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "quoteText", target = "quoteText")
    @Mapping(source = "quoteAuthor", target = "quoteAuthor")
    @Mapping(source = "quoteGenre", target = "quoteGenre")
    Quote mapToApiModel(com.tui.domain.model.Quote domainQuote);
}

