package com.example.vivian.capstone_test.persistence.profile;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.vivian.capstone_test.domain.entities.Profile;
import com.example.vivian.capstone_test.domain.values.Id;
import com.example.vivian.capstone_test.persistence.BlendDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by aashreys on 23/01/17.
 */

@Table(database = BlendDatabase.class)
public class ProfileModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @NonNull
    @Column
    String name;

    @NonNull
    @Column
    String phone;

    @NonNull
    @Column
    String email;

    @Nullable
    @Column
    String location;

    @Nullable
    @Column
    String imagePath;

    @Nullable
    @Column
    String interests;

    @Nullable
    @Column
    String tags;

    ProfileModel() {}

    ProfileModel(Profile profile) {
        this.name = profile.getName().getValue();
        this.phone = profile.getPhone().getValue();
        this.email = profile.getEmail().getValue();
        this.location = profile.getLocation() != null ? profile.getLocation().getValue() : null;
        this.imagePath = profile.getImagePath() != null ? profile.getImagePath().getValue() : null;
        if (profile.getInterests() != null) {
            this.interests = com.example.vivian.capstone_test.Utils.collectionToCsv(profile.getInterests());
        }
        if (profile.getTags() != null) {
            this.tags = com.example.vivian.capstone_test.Utils.collectionToCsv(profile.getTags());
        }
    }

    ProfileModel(Id id, Profile profile) {
        this(profile);
        this.id = id.getValue();
    }

    @Nullable
    String[] getInterestsArray() {
        return interests != null ? com.example.vivian.capstone_test.Utils.csvToStringArray(interests) : null;
    }

    @Nullable
    String[] getTagsArray() {
        return tags != null ?  com.example.vivian.capstone_test.Utils.csvToStringArray(tags) : null;
    }

}
