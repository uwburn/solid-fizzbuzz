package it.mgt.fizzbuzz.solid.rules;

import it.mgt.fizzbuzz.solid.interfaces.Rule;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CompositeRule implements Rule {

    private List<Rule> rules;

    public CompositeRule(Rule... rules) {
        this.rules = Arrays.asList(rules);
    }

    @Override
    public Optional<String> apply(int i) {
        return rules.stream()
                .map(r -> r.apply(i))
                .flatMap(Optional::stream)
                .reduce((s1, s2) -> s1 + " " + s2);
    }

}
