package com.example.vivian.capstone_test.domain;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

/**
 * A POJO for representing data in our code. This class represents basic data objects in our code
 * and encapsulates their validation logic.
 *
 * {@link T} - data type being encapsulated
 *
 * Created by aashreys on 20/01/17.
 */

public class Value<T> {

    @NonNull
    private final T value;

    protected Value(@NonNull T value) throws IncorrectValueException {
        if (value != null) {
            validate(value);
            this.value = value;
        } else {
            throw new IncorrectValueException("Value may not be null.");
        }
    }

    /**
     * Implement validation logic for data here
     * @param value - value to be validated
     * @throws IncorrectValueException
     */
    protected void validate(T value) throws IncorrectValueException {
        // Override this for validation logic
    }

    @NonNull
    public T getValue() {
        return value;
    }

    public static class IncorrectValueException extends Exception {

        protected IncorrectValueException() {
        }

        protected IncorrectValueException(String message) {
            super(message);
        }

        protected IncorrectValueException(String message, Throwable cause) {
            super(message, cause);
        }

        protected IncorrectValueException(Throwable cause) {
            super(cause);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        protected IncorrectValueException(
                String message,
                Throwable cause,
                boolean enableSuppression,
                boolean writableStackTrace
        ) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
