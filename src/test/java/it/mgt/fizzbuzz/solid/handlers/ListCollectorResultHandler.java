package it.mgt.fizzbuzz.solid.handlers;

import it.mgt.fizzbuzz.solid.interfaces.ResultHandler;

import java.util.ArrayList;
import java.util.List;

public class ListCollectorResultHandler implements ResultHandler {

    List<String> results = new ArrayList<>();

    @Override
    public void handle(String result) {
        results.add(result);
    }

    public String getResult(int index) {
        return results.get(index);
    }

    public int size() {
        return results.size();
    }
}
