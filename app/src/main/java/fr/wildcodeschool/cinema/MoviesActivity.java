package fr.wildcodeschool.cinema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.wildcodeschool.cinema.model.Movie;
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

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("titre de film 1"));
        movies.add(new Movie("titre de film 2"));
        movies.add(new Movie("titre de film 3"));
        movies.add(new Movie("titre de film 4"));

        ListView lvMovies = findViewById(R.id.lvMovies);
        MovieAdapter adapter = new MovieAdapter(MoviesActivity.this, movies);
        lvMovies.setAdapter(adapter);
    }
}
