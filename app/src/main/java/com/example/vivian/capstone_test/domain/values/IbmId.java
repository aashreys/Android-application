package com.example.vivian.capstone_test.domain.values;

import android.support.annotation.NonNull;

/**
 * Represents an IBM ID.
 * @see Value
 *
 * Created by aashreys on 20/01/17.
 */

public class IbmId extends StringValue {

    public IbmId(@NonNull String value) throws IncorrectValueException {
        super(value);
    }
}
