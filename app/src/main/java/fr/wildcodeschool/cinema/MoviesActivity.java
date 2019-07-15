package fr.wildcodeschool.cinema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.wildcodeschool.cinema.model.User;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        TextView tvEmail = findViewById(R.id.tvEmail);

        User user = UserSingleton.getInstance().getUser();
        if (user != null) {
            tvEmail.setText(user.getEmail());
        }
    }
}
