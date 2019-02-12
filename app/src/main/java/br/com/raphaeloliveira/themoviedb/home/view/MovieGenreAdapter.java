package br.com.raphaeloliveira.themoviedb.home.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.raphaeloliveira.themoviedb.R;
import br.com.raphaeloliveira.themoviedb.home.model.GenreAndMoviesResponse;
import br.com.raphaeloliveira.themoviedb.moviedetail.model.MovieDetail;
import br.com.raphaeloliveira.themoviedb.moviedetail.view.MovieClickInterface;
import br.com.raphaeloliveira.themoviedb.moviedetail.view.MovieDetailActivity;
import br.com.raphaeloliveira.themoviedb.moviedetail.view.MovieDetailAdapter;

import java.util.ArrayList;

public class MovieGenreAdapter extends RecyclerView.Adapter<MovieGenreAdapter.MyViewHolder> implements
        MovieClickInterface {

    private Context context;
    private ArrayList<GenreAndMoviesResponse> genreAndMovieList;

    private MovieDetailAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public MovieGenreAdapter(Context context, ArrayList<GenreAndMoviesResponse> genreAndMovieList) {
        this.context = context;
        this.genreAndMovieList = genreAndMovieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_view_genre, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        GenreAndMoviesResponse genreAndMovie = genreAndMovieList.get(position);
        holder.categoryTitle.setText(genreAndMovie.getCategoryName());

        mAdapter = new MovieDetailAdapter(context, genreAndMovie.getResults(), this);
        holder.movieList.setAdapter(mAdapter);
    }

    @Override
    public int getItemCount() {
        return genreAndMovieList != null ? genreAndMovieList.size() : 0;
    }

    @Override
    public void onItemClickListener(MovieDetail movieDetail) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("movieDetail", movieDetail);
        context.startActivity(intent);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;
        RecyclerView movieList;

         MyViewHolder(View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.title_category);
            movieList = itemView.findViewById(R.id.similar_movies_list);
        }
    }
}