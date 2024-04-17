package it.mgt.fizzbuzz.solid.rules;

import it.mgt.fizzbuzz.solid.interfaces.Rule;

import java.util.Optional;

abstract class ModuloRule implements Rule {

    private int divisor;
    private String replacement;

    protected ModuloRule(int divisor, String replacement) {
        this.divisor = divisor;
        this.replacement = replacement;
    }

    @Override
    public Optional<String> apply(int i) {
        if (i % divisor != 0) {
            return Optional.empty();
        }

        return Optional.of(replacement);
    }

}
