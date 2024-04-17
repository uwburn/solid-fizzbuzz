package it.mgt.fizzbuzz.solid.rules;

import it.mgt.fizzbuzz.solid.interfaces.Rule;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class NumberRuleTest {

    private Rule rule = new NumberRule();

    @Test
    public void number() {
        for (int i = 1; i <= 10; i++) {
            Optional<String> result = rule.apply(i);

            assertTrue(result.isPresent());
            assertEquals(String.valueOf(i), result.get());
        }
    }

}