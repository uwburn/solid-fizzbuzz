package it.mgt.fizzbuzz.solid.solvers;

import it.mgt.fizzbuzz.solid.interfaces.Rule;
import it.mgt.fizzbuzz.solid.handlers.ListCollectorResultHandler;
import it.mgt.fizzbuzz.solid.interfaces.Solver;
import it.mgt.fizzbuzz.solid.rules.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class StreamSolverTest {

    private static int LIMIT = 100;

    @Test
    public void fizzBuzz() {
        Rule fizzBuzzRule = new CompositeRule(
                new FizzRule(),
                new BuzzRule()
        );

        Rule overallRule = new FallbackRule(fizzBuzzRule, new NumberRule());

        ListCollectorResultHandler resultHandler = new ListCollectorResultHandler();

        Solver solver = new StreamSolver(overallRule, resultHandler);
        solver.solve(LIMIT);

        assertEquals(LIMIT, resultHandler.size());
        for (int i = 1 ; i <= LIMIT; i++) {
            if (i % 15 == 0) {
                assertEquals("fizz buzz", resultHandler.getResult(i - 1));
            }
            else if (i % 5 == 0) {
                assertEquals("buzz", resultHandler.getResult(i - 1));
            }
            else if (i % 3 == 0) {
                assertEquals("fizz", resultHandler.getResult(i - 1));
            }
            else {
                assertEquals(String.valueOf(i), resultHandler.getResult(i - 1));
            }
        }
    }
}