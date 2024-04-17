package it.mgt.fizzbuzz.solid.rules;

import it.mgt.fizzbuzz.solid.interfaces.Rule;

import java.util.Optional;

public class FallbackRule implements Rule {

    private Rule rule;
    private Rule fallbackRule;

    public FallbackRule(Rule rule, Rule fallbackRule) {
        this.rule = rule;
        this.fallbackRule = fallbackRule;
    }

    @Override
    public Optional<String> apply(int i) {
        Optional<String> result = rule.apply(i);

        if (result.isPresent()) {
            return result;
        }

        return fallbackRule.apply(i);
    }

}
