package com.example.vivian.capstone_test;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.vivian.capstone_test.domain.values.Value;

import java.util.Collection;

/**
 * Created by aashreys on 22/01/17.
 */

public class Utils {

    public static void configureButtonBar(Activity activity) {
        View buttonBarParent = activity.findViewById(android.R.id.content).findViewById(R.id
                .parent_button_bar);
        if (buttonBarParent != null) {
            Button profile, events, ping, rolln;
            profile = (Button) buttonBarParent.findViewById(R.id.button_profile);
            events = (Button) buttonBarParent.findViewById(R.id.button_events);
            ping = (Button) buttonBarParent.findViewById(R.id.button_ping);
            rolln = (Button) buttonBarParent.findViewById(R.id.button_rolln);
            setActivityForButton(profile, ProfileActivity.class);
            setActivityForButton(events, EventsActivity.class);
            setActivityForButton(ping, PingActivity.class);
            //        setActivityForButton(rolln, RollnActivity.class);
        }
    }

    private static <T extends Activity> void setActivityForButton(
            Button button, final Class<T>
            activityClass
    ) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), activityClass));
            }
        });
    }

    public static <T extends Value<String>> String collectionToCsv(Collection<T> collection) {
        String csvString = "";
        for (T collectionItem : collection) {
            csvString = csvString.concat(collectionItem.getValue()).concat(", ");
        }
        return csvString;
    }

    public static String[] csvToStringArray(String csv) {
        return csv.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }
}
