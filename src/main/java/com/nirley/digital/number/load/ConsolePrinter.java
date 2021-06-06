package com.nirley.digital.number.load;

import java.util.stream.Stream;

public class ConsolePrinter implements Printer {

    @Override
    public <T> void print(Stream<T> objectStream) {
        objectStream.map(String::valueOf)
                .forEach(this::print);
        nextLine();
    }

    @Override
    public<T> void print(T object) {
        System.out.print(object);
    }

    @Override
    public void nextLine() {
        System.out.println();
    }
}
