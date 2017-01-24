package com.example.vivian.capstone_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vivian.capstone_test.persistence.event.EventRepository;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Utils.configureButtonBar(this);
        EventRepository thisEvent = new EventRepository();
        thisEvent.getAll();
    }
}
