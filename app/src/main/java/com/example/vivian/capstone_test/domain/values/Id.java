package com.example.vivian.capstone_test.domain.values;

import android.support.annotation.NonNull;

/**
 * Created by aashreys on 24/01/17.
 */

public class Id extends Value<Long> {

    public Id(@NonNull Long value) throws IncorrectValueException {
        super(value);
    }

    @Override
    protected void validate(Long value) throws IncorrectValueException {
        if (value < 0) {
            throw new IncorrectValueException("Value may not be negative");
        }
    }
}
