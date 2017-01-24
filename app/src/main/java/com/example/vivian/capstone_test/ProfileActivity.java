package com.example.vivian.capstone_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vivian.capstone_test.domain.entities.Profile;
import com.example.vivian.capstone_test.domain.values.Email;
import com.example.vivian.capstone_test.domain.values.Id;
import com.example.vivian.capstone_test.domain.values.Interest;
import com.example.vivian.capstone_test.domain.values.Location;
import com.example.vivian.capstone_test.domain.values.Name;
import com.example.vivian.capstone_test.domain.values.Phone;
import com.example.vivian.capstone_test.domain.values.Tag;
import com.example.vivian.capstone_test.domain.values.Value;
import com.example.vivian.capstone_test.persistence.profile.ProfileRepository;
import com.example.vivian.capstone_test.persistence.profile.ThisProfileIdRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Utils.configureButtonBar(this);

        Button saveButton;
        ImageView profileImage;
        final EditText nameInput, phoneInput, emailInput, locationInput, interestsInput, tagsInput;

        saveButton = (Button) findViewById(R.id.button_save);
        profileImage = (ImageView) findViewById(R.id.image_profile_photo);
        nameInput = (EditText) findViewById(R.id.input_name);
        phoneInput = (EditText) findViewById(R.id.input_phone);
        emailInput = (EditText) findViewById(R.id.input_email);
        locationInput = (EditText) findViewById(R.id.input_location);
        interestsInput = (EditText) findViewById(R.id.input_interests);
        tagsInput = (EditText) findViewById(R.id.input_tags);

        // Load existing profile
        Profile thisProfile = getThisProfile();
        if (thisProfile != null) {
            nameInput.setText(thisProfile.getName().getValue());
            phoneInput.setText(thisProfile.getPhone().getValue());
            emailInput.setText(thisProfile.getEmail().getValue());
            if (thisProfile.getLocation() != null) {
                locationInput.setText(thisProfile.getLocation().getValue());
            }
            if (thisProfile.getInterests() != null) {
                interestsInput.setText(Utils.collectionToCsv(thisProfile.getInterests()));
            }
            if (thisProfile.getTags() != null) {
                tagsInput.setText(Utils.collectionToCsv(thisProfile.getTags()));
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String location = locationInput.getText().toString();
                    String[] interestStrings = Utils.csvToStringArray(interestsInput.getText()
                            .toString());
                    List<Interest> interests = new ArrayList<>();
                    for (String interest : interestStrings) {
                        if (!interest.isEmpty()) {
                            interests.add(new Interest(interest));
                        }
                    }
                    String[] tagStrings = Utils.csvToStringArray(tagsInput.getText().toString());
                    List<Tag> tags = new ArrayList<>();
                    for (String tag : tagStrings) {
                        if (!tag.isEmpty()) {
                            tags.add(new Tag(tag));
                        }
                    }
                    saveProfile(
                            new Name(nameInput.getText().toString()),
                            new Phone(phoneInput.getText().toString()),
                            new Email(emailInput.getText().toString()),
                            !location.isEmpty() ? new Location(location) : null,
                            interests,
                            tags
                    );
                } catch (Value.IncorrectValueException e) {
                    Toast.makeText(ProfileActivity.this, "Unable to save profile", Toast
                            .LENGTH_SHORT).show();
                }
            }
        });
    }

    @Nullable
    private Profile getThisProfile() {
        ThisProfileIdRepository thisProfileIdRepository = new ThisProfileIdRepository(this);
        ProfileRepository profileRepository = new ProfileRepository();
        return profileRepository.getThisProfile(thisProfileIdRepository);
    }

    private void saveProfile(
            Name name, Phone phone, Email email, Location location, Collection<Interest>
            interests, Collection<Tag> tags
    ) {
        ThisProfileIdRepository thisProfileIdRepository = new ThisProfileIdRepository(this);
        ProfileRepository profileRepository = new ProfileRepository();
        Profile thisProfile = new Profile(name, phone, email, location, null, interests, tags);
        long thisUserId = profileRepository.save(thisProfile);
        try {
            thisProfileIdRepository.save(new Id(thisUserId));
        } catch (Value.IncorrectValueException e) {
            e.printStackTrace();
        }
    }

}
