package com.example.vivian.capstone_test.persistence.event;

import android.support.annotation.Nullable;

import com.example.vivian.capstone_test.domain.entities.Event;
import com.example.vivian.capstone_test.domain.values.Location;
import com.example.vivian.capstone_test.domain.values.Name;
import com.example.vivian.capstone_test.domain.values.Note;
import com.example.vivian.capstone_test.domain.values.Tag;
import com.example.vivian.capstone_test.domain.values.Value;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by aashreys on 24/01/17.
 */

public class EventRepository {

    public long save(Event event) {
        EventModel model = new EventModel(event);
        model.save();
        return model.id;
    }

    public long add(Event event) {
        EventModel model = new EventModel(event);
        model.insert();
        return model.id;
    }

    public void delete(Event event) {
        // Implement this
    }

    public Event get(long id) {
        EventModel model = _get(id);
        if (model != null) {
            return _createEventFromModel(model);
        } else {
            return null;
        }
    }

    @Nullable
    private EventModel _get(long id) {
        return SQLite.select()
                .from(EventModel.class)
                .where(EventModel_Table.id.eq(id))
                .querySingle();
    }

    @Nullable
    private Event _createEventFromModel(EventModel model) {
        try {
            String[] inviteeStrings = model.getInviteesArray();
            List<Name> invitees = null;
            if (inviteeStrings != null) {
                invitees = new ArrayList<>();
                for (String invitee : inviteeStrings) {
                    try {
                        invitees.add(new Name(invitee));
                    } catch (Value.IncorrectValueException e) {
                        e.printStackTrace();
                    }
                }
            }

            String[] tagStrings = model.getTagsArray();
            List<Tag> tags = null;
            if (tagStrings != null) {
                tags = new ArrayList<>();
                for (String tag : tagStrings) {
                    try {
                        tags.add(new Tag(tag));
                    } catch (Value.IncorrectValueException e) {
                        e.printStackTrace();
                    }
                }
            }

            return new Event(
                    new Name(model.name),
                    model.date,
                    model.eventType,
                    model.location != null ? new Location(model.location) : null,
                    invitees,
                    tags,
                    model.notes != null ? new Note(model.notes) : null
            );
        } catch (Value.IncorrectValueException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Collection<Event> getAll() {
        List<EventModel> models = SQLite.select()
                .from(EventModel.class)
                .orderBy(EventModel_Table.date, false)
                .queryList();
        List<Event> events = new ArrayList<>();
        for (EventModel model : models) {
            Event event = _createEventFromModel(model);
            if (event != null) {
                events.add(event);
            }
        }
        return events;
    }

}
