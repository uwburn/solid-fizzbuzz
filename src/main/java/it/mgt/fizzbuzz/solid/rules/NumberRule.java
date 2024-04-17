package it.mgt.fizzbuzz.solid.rules;

import it.mgt.fizzbuzz.solid.interfaces.Rule;

import java.util.Optional;

public class NumberRule implements Rule {

    @Override
    public Optional<String> apply(int i) {
        return Optional.of(String.valueOf(i));
    }

}
