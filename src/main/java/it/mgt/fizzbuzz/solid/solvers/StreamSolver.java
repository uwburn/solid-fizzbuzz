package it.mgt.fizzbuzz.solid.solvers;

import it.mgt.fizzbuzz.solid.interfaces.ResultHandler;
import it.mgt.fizzbuzz.solid.interfaces.Rule;
import it.mgt.fizzbuzz.solid.interfaces.Solver;

import java.util.Optional;
import java.util.stream.IntStream;

public class StreamSolver implements Solver {

    private Rule rule;
    private ResultHandler resultHandler;

    public StreamSolver(Rule rule, ResultHandler resultHandler) {
        this.rule = rule;
        this.resultHandler = resultHandler;
    }

    @Override
    public void solve(int limit) {
        IntStream.rangeClosed(1, limit)
                .mapToObj(operand -> rule.apply(operand))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(r -> resultHandler.handle(r));
    }

}
