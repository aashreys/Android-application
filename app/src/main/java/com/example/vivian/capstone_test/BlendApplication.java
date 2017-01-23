package com.example.vivian.capstone_test;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by aashreys on 23/01/17.
 */

public class BlendApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize database
        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true).build());
    }
}
