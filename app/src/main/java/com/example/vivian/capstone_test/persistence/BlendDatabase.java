package com.example.vivian.capstone_test.persistence;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by aashreys on 23/01/17.
 */

@Database(name = BlendDatabase.NAME, version = BlendDatabase.VERSION)
public class BlendDatabase {

    public static final String NAME = "BlendDatabase"; // we will add the .db extension

    public static final int VERSION = 1;

}
