package fr.wildcodeschool.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.wildcodeschool.cinema.model.User;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button btGoToSignUp = findViewById(R.id.btGoToSignUp);
        btGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        Button btSignIn = findViewById(R.id.btGoToSignIn);
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etEmail = findViewById(R.id.etEmail);
                EditText etPassword = findViewById(R.id.etPassword);
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    new AlertDialog.Builder(SignInActivity.this)
                            .setTitle("Error")
                            .setMessage("Please fill your email and password")
                            .show();
                    return;
                }

                User user = new User();
                user.setEmail(email);
                user.setPassword(password);

                VolleySingleton.getInstance(SignInActivity.this)
                        .signIn(user, new Consumer<User>() {
                            @Override
                            public void accept(User user) {
                                UserSingleton.getInstance().setUser(user);
                                Intent intent = new Intent(SignInActivity.this,
                                        MoviesActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });
    }
}
