package fr.wildcodeschool.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import fr.wildcodeschool.cinema.model.User;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btGoToSignIn = findViewById(R.id.btGoToSignIn);
        btGoToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        Button btSignUp = findViewById(R.id.btGoToSignUp);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etEmail = findViewById(R.id.etEmail);
                EditText etPassword = findViewById(R.id.etPassword);
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    new AlertDialog.Builder(SignUpActivity.this)
                            .setTitle("Error")
                            .setMessage("Please fill your email and password")
                            .show();
                    return;
                }
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);

                VolleySingleton.getInstance(SignUpActivity.this).signUp(user, new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        UserSingleton.getInstance().setUser(user);
                        Intent intent = new Intent(SignUpActivity.this, MoviesActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
