package com.example.vivian.capstone_test.persistence.profile;

import android.content.Context;
import android.content.SharedPreferences;

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

    public void save(long thisProfileId) {
        sharedPreferences.edit().putLong(KEY_THIS_PROFILE_ID, thisProfileId).apply();
    }

    public long get() {
        long id = sharedPreferences.getLong(KEY_THIS_PROFILE_ID, -1);
        if (id >= 0) {
            return id;
        } else {
            throw new IllegalArgumentException("Cannot find a valid id for this profile");
        }
    }

}
