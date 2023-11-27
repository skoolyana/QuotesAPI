package com.tui.api.rest.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Spy;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.tui.api.rest.mapper.config.MapStructConfig;
import com.tui.domain.model.QuoteObjectMother;
import com.tui.model.Quote;

public class QuoteMapperTest {
	
    private QuoteMapper quoteMapper;

    @BeforeEach
    public void setUp() {
        this.quoteMapper = new QuoteMapperImpl();
    }
    
    private static Stream<com.tui.domain.model.Quote> supplyQuotes() {
        return Stream.of(
                QuoteObjectMother.anAgeQuote().build(),
                QuoteObjectMother.aReadingQuote().build(),
                QuoteObjectMother.anUninhibitedQuote().build()
        );
    }
    
    
    @ParameterizedTest
    @MethodSource("supplyQuotes")
    public void whenMappingQuotesShouldSuccessIndependently(com.tui.domain.model.Quote model) {
        validateMapper(model);
    }
    
    private void validateMapper(com.tui.domain.model.Quote model) {
        com.tui.model.Quote result = quoteMapper.mapToApiModel(model);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(model);
    }
    

}
