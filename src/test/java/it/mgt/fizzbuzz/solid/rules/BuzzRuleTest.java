package it.mgt.fizzbuzz.solid.rules;

import it.mgt.fizzbuzz.solid.interfaces.Rule;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class BuzzRuleTest {

    private Rule rule = new BuzzRule();

    @Test
    public void notFive() {
        Optional<String> result = rule.apply(1);

        assertTrue(result.isEmpty());
    }

    @Test
    public void five() {
        Optional<String> result = rule.apply(5);

        assertTrue(result.isPresent());
        assertEquals(result.get(), "buzz");
    }

}