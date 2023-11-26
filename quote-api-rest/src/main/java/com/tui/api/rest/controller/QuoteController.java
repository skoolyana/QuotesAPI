package com.tui.api.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.tui.api.QuotesApi;
import com.tui.api.rest.mapper.QuoteMapper;
import com.tui.application.usecase.GetQuoteUseCase;
import com.tui.model.PagedGetAllQuotesResponse;
import com.tui.model.PagedGetAllQuotesResponsePageable;
import com.tui.model.Quote;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class QuoteController implements QuotesApi {
	
	
	private final GetQuoteUseCase getQuoteUseCase;
	
	private final QuoteMapper quoteMapper;

	@Override
	public ResponseEntity<Quote> getQuoteById(String id) {
				
		Optional<com.tui.domain.model.Quote> quoteDomain =getQuoteUseCase.getQuoteById(id);
			
		return quoteDomain.map(quote -> ResponseEntity.ok(quoteMapper.mapToApiModel(quote)))
                .orElseGet(() -> ResponseEntity.ok().build());

	}

	@Override
	public ResponseEntity<List<Quote>> getQuotesByAuthor(String author) {
		
		List<com.tui.domain.model.Quote> quoteDomain =getQuoteUseCase.getQuoteByAuthor(author);

		 List<Quote> quotes = quoteDomain.stream()
		            .map(quoteMapper::mapToApiModel)
		            .collect(Collectors.toList());

		    return ResponseEntity.ok(quotes);
		}

	@Override
	public ResponseEntity<PagedGetAllQuotesResponse> getAllQuotes(@Valid Integer page, @Valid Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		
		Map<String,Object> quotesMap =getQuoteUseCase.getAllQuotes(page, size);

		List<com.tui.domain.model.Quote> quotes = (List<com.tui.domain.model.Quote>) quotesMap.get("result");
	    long totalElements = (long) quotesMap.get("totalElements");
	    int totalPages = (int) quotesMap.get("pages");
		

	    PagedGetAllQuotesResponse response = new PagedGetAllQuotesResponse()
	            .content(quotes.stream().map(quoteMapper::mapToApiModel).collect(Collectors.toList()));

	    PagedGetAllQuotesResponsePageable responsePageable = new PagedGetAllQuotesResponsePageable()
	    		.offset((int) pageable.getOffset())
	            .pageNumber(pageable.getPageNumber())
	            .pageSize(pageable.getPageSize())
	            .paged(pageable.isPaged())
	            .unpaged(pageable.isUnpaged());
	            
	    response.setTotalElements((int) totalElements);
	    response.totalPages(totalPages);
	    response.pageable(responsePageable);

	    return ResponseEntity.ok(response);
	}
	
}
