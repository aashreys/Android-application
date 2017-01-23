package com.example.vivian.capstone_test.domain.values;

import android.support.annotation.NonNull;

/**
 * Created by aashreys on 23/01/17.
 */

public class Location extends Value<String> {

    public Location(@NonNull String value) throws IncorrectValueException {
        super(value);
    }
}
