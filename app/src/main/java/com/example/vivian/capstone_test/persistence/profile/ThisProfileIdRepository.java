package com.example.vivian.capstone_test.persistence.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.vivian.capstone_test.domain.values.Id;
import com.example.vivian.capstone_test.domain.values.Value;

/**
 * Created by aashreys on 23/01/17.
 */

public class ThisProfileIdRepository {

    private static final String NAME = "blend_this_profile_shared_pref";

    private static final String KEY_THIS_PROFILE_ID = "blend_this_profile_id_key";

    private SharedPreferences sharedPreferences;

    public ThisProfileIdRepository(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void update(Id thisProfileId) {
        sharedPreferences.edit().putLong(KEY_THIS_PROFILE_ID, thisProfileId.getValue()).apply();
    }

    @Nullable
    public Id get() {
        long id = sharedPreferences.getLong(KEY_THIS_PROFILE_ID, -1);
        try {
            return new Id(id);
        } catch (Value.IncorrectValueException e) {
            e.printStackTrace();
            return null;
        }
    }

}
