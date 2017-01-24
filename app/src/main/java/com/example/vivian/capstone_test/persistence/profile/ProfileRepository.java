package com.example.vivian.capstone_test.persistence.profile;

import android.support.annotation.Nullable;

import com.example.vivian.capstone_test.domain.entities.Profile;
import com.example.vivian.capstone_test.domain.values.Email;
import com.example.vivian.capstone_test.domain.values.Id;
import com.example.vivian.capstone_test.domain.values.ImagePath;
import com.example.vivian.capstone_test.domain.values.Interest;
import com.example.vivian.capstone_test.domain.values.Location;
import com.example.vivian.capstone_test.domain.values.Name;
import com.example.vivian.capstone_test.domain.values.Phone;
import com.example.vivian.capstone_test.domain.values.Tag;
import com.example.vivian.capstone_test.domain.values.Value;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aashreys on 23/01/17.
 */

public class ProfileRepository {

    public Id insert(Profile profile) {
        try {
            ProfileModel model = new ProfileModel(profile);
            model.insert();
            return new Id(model.id);
        } catch (Value.IncorrectValueException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean exists(Id profileId) {
        ProfileModel model = SQLite.select()
                .from(ProfileModel.class)
                .where(ProfileModel_Table.id.eq(profileId.getValue()))
                .querySingle();
        return model != null;
    }

    public void update(Id id, Profile profile) {
        ProfileModel model = new ProfileModel(id, profile);
        model.update();
    }

    @Nullable
    public Profile get(Id id) {
        if (id != null) {
            ProfileModel model = SQLite.select()
                    .from(ProfileModel.class)
                    .where(ProfileModel_Table.id.eq(id.getValue()))
                    .querySingle();
            if (model != null) {
                return _createProfileFromModel(model);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private Profile _createProfileFromModel(ProfileModel model) {
        try {
            String[] interestsStrings = model.getInterestsArray();
            List<Interest> interests = null;
            if (interestsStrings != null) {
                interests = new ArrayList<>(interestsStrings.length);
                for (String interestString : interestsStrings) {
                    try {
                        interests.add(new Interest(interestString));
                    } catch (Value.IncorrectValueException e) {
                        e.printStackTrace();
                    }
                }
            }
            String[] tagsStrings = model.getTagsArray();
            List<Tag> tags = null;
            if (tagsStrings != null) {
                tags = new ArrayList<>(tagsStrings.length);
                for (String tagString : tagsStrings) {
                    try {
                        tags.add(new Tag(tagString));
                    } catch (Value.IncorrectValueException e) {
                        e.printStackTrace();
                    }
                }
            }
            return new Profile(
                    new Name(model.name),
                    new Phone(model.phone),
                    new Email(model.email),
                    model.location != null ? new Location(model.location) : null,
                    model.imagePath != null ? new ImagePath(model.imagePath) : null,
                    interests,
                    tags
            );
        } catch (Value.IncorrectValueException e) {
            e.printStackTrace();
            return null;
        }
    }
}
