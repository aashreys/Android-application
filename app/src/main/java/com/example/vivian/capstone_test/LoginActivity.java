package com.example.vivian.capstone_test;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vivian.capstone_test.domain.IbmId;
import com.example.vivian.capstone_test.domain.Password;
import com.example.vivian.capstone_test.domain.Value;

public class LoginActivity extends AppCompatActivity {

    private EditText ibmIdInput, passwordInput;

    private Button loginButton;

    /**
     * Common click handler so that onClick code doesn't spread everywhere like it usually does.
     */
    private final View.OnClickListener commonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onViewClick(view.getId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ibmIdInput = (EditText) findViewById(R.id.input_ibm_id);
        passwordInput = (EditText) findViewById(R.id.input_password);

        loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(commonClickListener);
    }

    private void onViewClick(int viewId) {
        switch (viewId) {
            case R.id.button_login:
                onLoginButtonClicked();
                break;

            default:
        }

    }

    private void onLoginButtonClicked() {
        IbmId ibmId;
        Password password;
        @StringRes Integer errorMessage = null;
        try {
            ibmId = new IbmId(ibmIdInput.getText().toString());
        } catch (Value.IncorrectValueException e) {
            errorMessage = R.string.error_incorrect_ibm_id;
            e.printStackTrace();
        }
        try {
            password = new Password(passwordInput.getText().toString());
        } catch (Value.IncorrectValueException e) {
            errorMessage = errorMessage != null ? R.string.error_incorrect_ibm_id_and_password : R
                    .string.error_incorrect_password;
            e.printStackTrace();
        }
        if (errorMessage == null) {
            // Do login
        } else {
            displayMessage(errorMessage, false);
        }
    }

    private void displayMessage(@StringRes Integer message, boolean isLongToast) {
        // Snackbars, because Toast messages are ancient!
        Snackbar.make(loginButton, message, isLongToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)
                .show();
    }
}
