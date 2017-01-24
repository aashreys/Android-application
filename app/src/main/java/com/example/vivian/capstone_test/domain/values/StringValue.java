package com.example.vivian.capstone_test.domain.values;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

/**
 * Created by aashreys on 24/01/17.
 */

public class StringValue extends Value<String> {

    protected StringValue(@NonNull String value) throws IncorrectValueException {
        super(value);
    }

    protected boolean isEmptyStringAllowed() {
        return false;
    }

    @CallSuper
    @Override
    protected void validate(String value) throws IncorrectValueException {
        if (!isEmptyStringAllowed() && value.isEmpty()) {
            throw new IncorrectValueException("Value may not be empty");
        }
    }
}
