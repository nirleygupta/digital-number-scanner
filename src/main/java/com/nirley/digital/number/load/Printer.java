package com.nirley.digital.number.load;

import java.util.stream.Stream;

public interface Printer {

    <T> void print(Stream<T> objectStream);

    <T> void print(T object);

    void nextLine();
}
