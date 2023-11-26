package com.tui.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {

	 	private String id;
	    private String quoteText;
	    private String quoteAuthor;
	    private String quoteGenre;
	    private Integer v;
	
}
