package com.example.vivian.capstone_test.persistence.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.vivian.capstone_test.domain.entities.Event;
import com.example.vivian.capstone_test.domain.values.EventType;
import com.example.vivian.capstone_test.persistence.BlendDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by aashreys on 24/01/17.
 */

@Table(database = BlendDatabase.class)
public class EventModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @NonNull
    @Column
    String name;

    @NonNull
    @Column
    Date date;

    @NonNull
    @Column
    EventType eventType;

    @Nullable
    @Column
    String location;

    @Nullable
    @Column
    String invitees;

    @Nullable
    @Column
    String tags;

    @Nullable
    @Column
    String notes;

    EventModel() {}

    EventModel(Event event) {
        this.name = event.getName().getValue();
        this.date = event.getDate();
        this.eventType = event.getEventType();
        this.location = event.getLocation() != null ? event.getLocation().getValue() : null;
        if (event.getInvitees() != null) {
            this.invitees = com.example.vivian.capstone_test.Utils.collectionToCsv(event.getInvitees());
        }
        if (event.getTags() != null) {
            this.tags = com.example.vivian.capstone_test.Utils.collectionToCsv(event.getTags());
        }
        this.notes = event.getNote() != null ? event.getNote().getValue() : null;
    }

    String[] getInviteesArray() {
        return invitees != null ? com.example.vivian.capstone_test.Utils.csvToStringArray(invitees) : null;
    }

    String[] getTagsArray() {
        return tags != null ? com.example.vivian.capstone_test.Utils.csvToStringArray(tags) : null;
    }

}
