package com.example.vivian.capstone_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.vivian.capstone_test.domain.entities.Event;
import com.example.vivian.capstone_test.domain.values.EventType;
import com.example.vivian.capstone_test.domain.values.Location;
import com.example.vivian.capstone_test.domain.values.Name;
import com.example.vivian.capstone_test.domain.values.Note;
import com.example.vivian.capstone_test.domain.values.Tag;
import com.example.vivian.capstone_test.domain.values.Value;
import com.example.vivian.capstone_test.persistence.event.EventRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Utils.configureButtonBar(this);

        final EditText eventNameInput, dateTimeInput, eventLocationInput, inviteesInput, tagsInput,
                notesInput;
        final RadioButton publicRadioButton, privateRadioButton;
        Button saveButton;

        eventNameInput = (EditText) findViewById(R.id.input_event_name);
        dateTimeInput = (EditText) findViewById(R.id.input_date_time);
        eventLocationInput = (EditText) findViewById(R.id.input_event_location);
        inviteesInput = (EditText) findViewById(R.id.input_invitees);
        tagsInput = (EditText) findViewById(R.id.input_tags);
        notesInput = (EditText) findViewById(R.id.input_notes);

        publicRadioButton = (RadioButton) findViewById(R.id.radio_button_public);
        privateRadioButton = (RadioButton) findViewById(R.id.radio_button_private);

        saveButton = (Button) findViewById(R.id.button_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String locationString = eventLocationInput.getText().toString();
                    EventType eventType = EventType.PUBLIC;
                    if (publicRadioButton.isSelected()) {
                        eventType = EventType.PUBLIC;
                    }
                    if (privateRadioButton.isSelected()) {
                        eventType = EventType.PRIVATE;
                    }

                    String[] inviteeStrings = Utils.csvToStringArray(inviteesInput.getText()
                            .toString());
                    List<Name> invitees = new ArrayList<>();
                    for (String invitee : inviteeStrings) {
                        if (!invitee.isEmpty()) {
                            invitees.add(new Name(invitee));
                        }
                    }

                    String[] tagStrings = Utils.csvToStringArray(tagsInput.getText().toString());
                    List<Tag> tags = new ArrayList<>();
                    for (String tag : tagStrings) {
                        if (!tag.isEmpty()) {
                            tags.add(new Tag(tag));
                        }
                    }
                    addEvent(
                            new Name(eventNameInput.getText().toString()),
                            new Date(3441553),
                            eventType,
                            !locationString.isEmpty() ? new Location(locationString) : null,
                            invitees,
                            tags,
                            new Note(notesInput.getText().toString())
                    );
                } catch (Value.IncorrectValueException e) {
                    Toast.makeText(
                            CreateEventActivity.this,
                            "Unable to create event",
                            Toast.LENGTH_SHORT
                    ).show();

                }
            }
        });

    }

    private void addEvent(
            Name name, Date date, EventType eventType, Location location,
            Collection<Name> invitees, Collection<Tag> tags, Note note
    ) {
        EventRepository eventRepository = new EventRepository();
        Event event = new Event(name, date, eventType, location, invitees, tags, note);
        eventRepository.add(event);
    }

}
