package it.mgt.fizzbuzz.solid;

import it.mgt.fizzbuzz.solid.interfaces.Rule;
import it.mgt.fizzbuzz.solid.interfaces.Solver;
import it.mgt.fizzbuzz.solid.solvers.StreamSolver;
import it.mgt.fizzbuzz.solid.handlers.ConsoleResultHandler;
import it.mgt.fizzbuzz.solid.rules.*;

public class Main {

    public static void main(String[] args) {
        Rule fizzBuzzRule = new CompositeRule(
                new FizzRule(),
                new BuzzRule()
        );

        Rule overallRule = new FallbackRule(fizzBuzzRule, new NumberRule());

        Solver solver = new StreamSolver(overallRule, new ConsoleResultHandler());
        solver.solve(100);
    }

}

