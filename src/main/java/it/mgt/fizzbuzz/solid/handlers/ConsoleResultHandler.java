package it.mgt.fizzbuzz.solid.handlers;

import it.mgt.fizzbuzz.solid.interfaces.ResultHandler;

public class ConsoleResultHandler implements ResultHandler {

    @Override
    public void handle(String check) {
        System.out.println(check);
    }

}
