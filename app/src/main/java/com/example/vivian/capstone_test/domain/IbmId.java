package com.example.vivian.capstone_test.domain;

import android.support.annotation.NonNull;

/**
 * Created by aashreys on 20/01/17.
 */

public class IbmId extends Value<String> {

    public IbmId(@NonNull String value) throws IncorrectValueException {
        super(value);
    }

    @Override
    protected void validate(String value) throws IncorrectValueException {
        if (value.isEmpty()) {
            throw new IncorrectValueException("Value may not be empty.");
        }
    }
}
