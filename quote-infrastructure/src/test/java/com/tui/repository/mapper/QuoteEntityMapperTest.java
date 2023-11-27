package com.tui.repository.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.tui.repository.entity.QuoteEntity;
import com.tui.repository.entity.QuoteEntityObjectMother;

public class QuoteEntityMapperTest {

    private QuoteEntityMapper quoteEntityMapper;

    @BeforeEach
    public void setUp() {
        this.quoteEntityMapper = QuoteEntityMapper.INSTANCE;
    }

    private static Stream<QuoteEntity> supplyQuoteEntities() {
        return Stream.of(
               
        		 QuoteEntityObjectMother.anAgeQuoteEntity(),
        		 QuoteEntityObjectMother.anUninhibitedQuoteEntity(),
        		 QuoteEntityObjectMother.aReadingQuoteEntity()
        	        		
        );
    }

    @ParameterizedTest
    @MethodSource("supplyQuoteEntities")
    public void whenMappingQuoteEntitiesShouldSuccessIndependently(QuoteEntity entity) {
        validateMapper(entity);
    }

    private void validateMapper(QuoteEntity entity) {
        com.tui.domain.model.Quote result = quoteEntityMapper.mapToDomainModel(entity);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }
}

