package com.example.vivian.capstone_test.domain.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.vivian.capstone_test.domain.values.EventType;
import com.example.vivian.capstone_test.domain.values.Location;
import com.example.vivian.capstone_test.domain.values.Name;
import com.example.vivian.capstone_test.domain.values.Note;
import com.example.vivian.capstone_test.domain.values.Tag;

import java.util.Collection;
import java.util.Date;

/**
 * Created by aashreys on 23/01/17.
 */

public class Event {

    @NonNull
    private final Name name;

    @NonNull
    private final Date date;

    @NonNull
    private EventType eventType;

    @Nullable
    private Location location;

    @Nullable
    private Collection<Name> invitees;

    @Nullable
    private Collection<Tag> tags;

    // TODO: Add support for multiple notes
    @Nullable
    private Note note;

    public Event(
            @NonNull Name name,
            @NonNull Date date,
            @NonNull EventType eventType,
            @Nullable Location location,
            @Nullable Collection<Name> invitees,
            @Nullable Collection<Tag> tags,
            @Nullable Note note
    ) {
        this.name = name;
        this.date = date;
        this.eventType = eventType;
        this.location = location;
        this.invitees = invitees;
        this.tags = tags;
        this.note = note;
    }

    @NonNull
    public Name getName() {
        return name;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    @NonNull
    public EventType getEventType() {
        return eventType;
    }

    @Nullable
    public Location getLocation() {
        return location;
    }

    @Nullable
    public Collection<Name> getInvitees() {
        return invitees;
    }

    @Nullable
    public Collection<Tag> getTags() {
        return tags;
    }

    @Nullable
    public Note getNote() {
        return note;
    }
}
