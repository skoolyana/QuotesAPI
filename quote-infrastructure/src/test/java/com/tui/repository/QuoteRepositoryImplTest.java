package com.tui.repository;

import com.tui.repository.entity.QuoteEntity;
import com.tui.repository.entity.QuoteEntityObjectMother;
import com.tui.repository.mapper.QuoteEntityMapper;
import com.tui.domain.model.Quote;
import com.tui.domain.model.QuoteObjectMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuoteRepositoryImplTest {

    @Mock
    private MongoQuoteRepository mongoQuoteRepository;

    @Mock
    private QuoteEntityMapper quoteEntityMapper;

    @InjectMocks
    private QuoteRepositoryImpl quoteRepository;

    @Test
    void findByAuthor() {
        // Arrange
        String author = "TestAuthor";
        List<QuoteEntity> entities = Collections.singletonList(QuoteEntityObjectMother.anAgeQuoteEntity());
        when(mongoQuoteRepository.findByQuoteAuthor(author)).thenReturn(entities);
        when(quoteEntityMapper.mapToDomainModel(any())).thenReturn(QuoteObjectMother.anAgeQuote().build());

        // Act
        List<Quote> result = quoteRepository.findByAuthor(author);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getQuoteAuthor()).isEqualTo("William Osler");
    }

    @Test
    void findById() {
        // Arrange
        String id = "TestId";
        Optional<QuoteEntity> optionalEntity = Optional.of(QuoteEntityObjectMother.anAgeQuoteEntity());
        when(mongoQuoteRepository.findById(id)).thenReturn(optionalEntity);
        when(quoteEntityMapper.mapToDomainModel(any())).thenReturn(QuoteObjectMother.anAgeQuote().build());

        // Act
        Optional<Quote> result = quoteRepository.findById(id);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getQuoteAuthor()).isEqualTo("William Osler");
    }

    @Test
    void getAllQuotes() {
        // Arrange
        int page = 0;
        int size = 10;
        List<QuoteEntity> entities = Collections.singletonList(QuoteEntityObjectMother.anAgeQuoteEntity());
        Page<QuoteEntity> quoteEntityPage = new PageImpl<>(entities, PageRequest.of(page, size), entities.size());
        when(mongoQuoteRepository.findAll(any(PageRequest.class))).thenReturn(quoteEntityPage);
        when(quoteEntityMapper.mapToDomainModel(any())).thenReturn(QuoteObjectMother.anAgeQuote().build());

        // Act
        Map<String, Object> resultMap = quoteRepository.getAllQuotes(page, size);

        // Assert
        assertThat(resultMap).containsKeys("result", "totalElements", "pages");
        List<Quote> result = (List<Quote>) resultMap.get("result");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getQuoteAuthor()).isEqualTo("William Osler");
    }
}

