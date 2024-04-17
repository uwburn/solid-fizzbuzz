package it.mgt.fizzbuzz.solid.rules;

import it.mgt.fizzbuzz.solid.interfaces.Rule;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class CompositeRuleTest {

    private Rule rule = new CompositeRule(
            new FizzRule(),
            new BuzzRule()
    );

    @Test
    public void none() {
        Optional<String> result = rule.apply(1);

        assertTrue(result.isEmpty());
    }

    @Test
    public void first() {
        Optional<String> result = rule.apply(3);

        assertTrue(result.isPresent());
        assertEquals("fizz", result.get());
    }

    @Test
    public void second() {
        Optional<String> result = rule.apply(5);

        assertTrue(result.isPresent());
        assertEquals("buzz", result.get());
    }

    @Test
    public void both() {
        Optional<String> result = rule.apply(15);

        assertTrue(result.isPresent());
        assertEquals("fizz buzz", result.get());
    }

}