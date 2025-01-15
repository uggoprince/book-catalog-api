package com.book_catalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestGreeter {
    private String greeter;

    @BeforeEach
    public void setup() {
        greeter = "Hey world, how are you? Good morning";
    }

    @Test
    public void greetSomeone() {
        String someone = "world";
        assertThat(greeter, containsString(someone));
    }

    @Test
    public void greetSomeoneLength() {
        String someone = "World";
        assertThat(greeter.length(), is(greaterThan(someone.length())));
    }

    @Test
    public void greetSomeoneLengthGreater() {
        String someone = "World, Thanks you making me happy";
        assertThat(greeter.length(), is(greaterThan(someone.length())));
    }
}
