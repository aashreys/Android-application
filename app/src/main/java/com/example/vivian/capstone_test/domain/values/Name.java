package com.example.vivian.capstone_test.domain.values;

import android.support.annotation.NonNull;

/**
 * Created by aashreys on 23/01/17.
 */

public class Name extends Value<String> {

    public Name(@NonNull String value) throws IncorrectValueException {
        super(value);
    }
}
