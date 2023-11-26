package com.tui.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "quotes")
public class QuoteEntity {
	
	@Id
    @Field("_id")
    private String id;

    @Field("quoteText")
    private String quoteText;

    @Field("quoteAuthor")
    private String quoteAuthor;

    @Field("quoteGenre")
    private String quoteGenre;

    @Field("__v")
    private Integer v;
}
