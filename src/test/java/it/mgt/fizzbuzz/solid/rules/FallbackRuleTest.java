package it.mgt.fizzbuzz.solid.rules;

import it.mgt.fizzbuzz.solid.interfaces.Rule;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class FallbackRuleTest {

    private Rule rule = new FallbackRule(
            new FizzRule(),
            new NumberRule()
    );

    @Test
    public void rule() {
        Optional<String> result = rule.apply(3);

        assertTrue(result.isPresent());
        assertEquals("fizz", result.get());
    }

    @Test
    public void fallback() {
        Optional<String> result = rule.apply(1);

        assertTrue(result.isPresent());
        assertEquals("1", result.get());
    }

}