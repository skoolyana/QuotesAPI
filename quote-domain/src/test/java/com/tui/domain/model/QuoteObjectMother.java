package com.tui.domain.model;

import com.tui.domain.model.Quote.QuoteBuilder;

public final class QuoteObjectMother {

    public static final Quote AGE_QUOTE = anAgeQuote().build();
    public static final Quote READING_QUOTE = aReadingQuote().build();
    public static final Quote UNINHIBITED_QUOTE = anUninhibitedQuote().build();

    public static QuoteBuilder anAgeQuote() {
        return Quote.builder()
                .id("5eb17aadb69dc744b4e70e48")
                .quoteText("The philosophies of one age have become the absurdities of the next, and the foolishness of yesterday has become the wisdom of tomorrow.")
                .quoteAuthor("William Osler")
                .quoteGenre("age");
    }

    public static QuoteBuilder aReadingQuote() {
        return Quote.builder()
                .id("5eb17aadb69dc744b4e70d92")
                .quoteText("Reading, after a certain age, diverts the mind too much from its creative pursuits. Any man who reads too much and uses his own brain too little falls into lazy habits of thinking.")
                .quoteAuthor("Albert Einstein")
                .quoteGenre("age");
    }

    public static QuoteBuilder anUninhibitedQuote() {
        return Quote.builder()
                .id("5eb17aadb69dc744b4e70f70")
                .quoteText("I think I'm a bit less inhibited, and not thinking too much before speaking. It's not about being shameful, I'm just a bit more unabashedly myself because of this thing, and it probably started at age 15. I can be around people and say what I think without fear.")
                .quoteAuthor("Kristen Stewart")
                .quoteGenre("age");
    }

}

