package com.example.vivian.capstone_test.domain.values;

import android.support.annotation.NonNull;

/**
 * Created by aashreys on 24/01/17.
 */

public class Note extends Value<String> {

    public Note(@NonNull String value) throws IncorrectValueException {
        super(value);
    }
}
