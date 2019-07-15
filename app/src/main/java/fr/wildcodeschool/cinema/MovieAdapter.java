package fr.wildcodeschool.cinema;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.wildcodeschool.cinema.model.Movie;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_movie, parent, false);
        }
        Movie movie = getItem(position);
        TextView tvMovieTitle = convertView.findViewById(R.id.tvMovieTitle);
        tvMovieTitle.setText(movie.getTitle());

        return convertView;
    }
}
