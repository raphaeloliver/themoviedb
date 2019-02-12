package br.com.raphaeloliveira.themoviedb.moviedetail.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import base.Utils;
import base.baseactivity.BaseActivity;
import br.com.raphaeloliveira.themoviedb.R;
import br.com.raphaeloliveira.themoviedb.home.view.MovieGenreActivity_;
import br.com.raphaeloliveira.themoviedb.moviedetail.contract.MovieDetailContract;
import br.com.raphaeloliveira.themoviedb.moviedetail.model.MovieDetail;
import br.com.raphaeloliveira.themoviedb.moviedetail.presenter.MovieDetailPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MovieDetailActivity extends BaseActivity implements MovieDetailContract.View, MovieClickInterface {

    private Toolbar toolbar;

    private ImageView imageMovie;
    private ImageView imageBackground;

    private TextView movieTitle;
    private TextView movieTime;
    private TextView movieDetailRate;
    private TextView movieDetailDescription;

    private RecyclerView similarMoviesList;

    private MovieDetailContract.Presenter presenter = new MovieDetailPresenter();

    private MovieDetailAdapter mAdapter;

    private MovieDetail movieDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        presenter.attachView(this);

        configToolbar();
        initViews();

        //POTTER AQUI
        MovieGenreActivity_.intent(this);
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        imageMovie = findViewById(R.id.image_movie);
        imageBackground = findViewById(R.id.image_background);
        movieTitle = findViewById(R.id.movie_title);
        movieTime = findViewById(R.id.movie_time);
        movieDetailRate = findViewById(R.id.movie_detail_rate);
        movieDetailDescription = findViewById(R.id.movie_detail_description);
        similarMoviesList = findViewById(R.id.similar_movies_list);

        if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable("movieDetail") != null) {
            movieDetail = (MovieDetail) getIntent().getExtras().getSerializable("movieDetail");

            DateFormat format = new SimpleDateFormat("yyyy", Locale.getDefault());

            try {
                Date date = format.parse(movieDetail.getReleaseDate());
                movieTime.setText(format.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            movieTitle.setText(movieDetail.getTitle());
            movieDetailRate.setText(String.valueOf(movieDetail.getRate()));
            movieDetailDescription.setText(movieDetail.getOverview());

            requestSimilarMovies(movieDetail);
            Utils.loadImage(this, movieDetail.getPosterPath(), imageMovie);
            Utils.loadImage(this, movieDetail.getBackdropPath(), imageBackground);
        }

    }

    private void configToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setAdapter(List<MovieDetail> movieDetailList) {
        mAdapter = new MovieDetailAdapter(this, movieDetailList, this);
        similarMoviesList.setAdapter(mAdapter);
    }

    @Override
    public void onItemClickListener(MovieDetail movieDetail) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movieDetail", movieDetail);
        startActivity(intent);
    }

    private void requestSimilarMovies(MovieDetail movieDetail) {
        presenter.requestSimilar(movieDetail);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        setUpSearchView(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}