package com.example.vivian.capstone_test.domain.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.vivian.capstone_test.domain.values.Email;
import com.example.vivian.capstone_test.domain.values.ImagePath;
import com.example.vivian.capstone_test.domain.values.Interest;
import com.example.vivian.capstone_test.domain.values.Location;
import com.example.vivian.capstone_test.domain.values.Name;
import com.example.vivian.capstone_test.domain.values.Phone;
import com.example.vivian.capstone_test.domain.values.Tag;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by aashreys on 23/01/17.
 */

public class Profile {

    @NonNull
    private final Name name;

    @NonNull
    private final Phone phone;

    @NonNull
    private final Email email;

    @Nullable
    private final Location location;

    @Nullable
    private final ImagePath imagePath;

    @Nullable
    private final Collection<Interest> interests;

    @Nullable
    private final Collection<Tag> tags;

    public Profile(
            @NonNull Name name,
            @NonNull Phone phone,
            @NonNull Email email,
            @Nullable Location location,
            @Nullable ImagePath imagePath,
            @Nullable Collection<Interest> interests,
            @Nullable Collection<Tag> tags
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.imagePath = imagePath;
        this.interests = interests != null ? new HashSet<>(interests) : null;
        this.tags = tags != null ? new HashSet<>(tags) : null;
    }

    @NonNull
    public Name getName() {
        return name;
    }

    @NonNull
    public Phone getPhone() {
        return phone;
    }

    @NonNull
    public Email getEmail() {
        return email;
    }

    @Nullable
    public Location getLocation() {
        return location;
    }

    @Nullable
    public ImagePath getImagePath() {
        return imagePath;
    }

    @Nullable
    public Collection<Interest> getInterests() {
        return interests;
    }

    @Nullable
    public Collection<Tag> getTags() {
        return tags;
    }
}
