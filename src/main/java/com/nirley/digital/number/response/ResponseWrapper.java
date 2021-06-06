package com.nirley.digital.number.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseWrapper {

    boolean error;

    Character character;

    public String toString() {
        return Character.toString(character);
    }
}
