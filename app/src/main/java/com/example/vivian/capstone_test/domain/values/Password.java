package com.example.vivian.capstone_test.domain.values;

import android.support.annotation.NonNull;

/**
 * Represents a password.
 * @see Value
 *
 * Created by aashreys on 20/01/17.
 */

public class Password extends StringValue {

    public Password(@NonNull String value) throws IncorrectValueException {
        super(value);
    }
}
