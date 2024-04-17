package it.mgt.fizzbuzz.solid.rules;

import it.mgt.fizzbuzz.solid.interfaces.Rule;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class FizzRuleTest {

    private Rule rule = new FizzRule();

    @Test
    public void notThree() {
        Optional<String> result = rule.apply(1);

        assertTrue(result.isEmpty());
    }

    @Test
    public void three() {
        Optional<String> result = rule.apply(3);

        assertTrue(result.isPresent());
        assertEquals(result.get(), "fizz");
    }

}