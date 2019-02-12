package br.com.raphaeloliveira.themoviedb.searchmovies.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import base.api.SyncInterface;
import base.baseactivity.BaseActivity;
import br.com.raphaeloliveira.themoviedb.R;
import br.com.raphaeloliveira.themoviedb.home.model.GenreAndMoviesResponse;
import br.com.raphaeloliveira.themoviedb.home.view.MovieGenreAdapter;
import br.com.raphaeloliveira.themoviedb.moviedetail.model.MovieDetail;
import br.com.raphaeloliveira.themoviedb.moviedetail.view.MovieClickInterface;
import br.com.raphaeloliveira.themoviedb.moviedetail.view.MovieDetailActivity;
import br.com.raphaeloliveira.themoviedb.searchmovies.contract.SearchMoviesContract;
import br.com.raphaeloliveira.themoviedb.searchmovies.presenter.SerchMoviesPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchMoviesActivity extends BaseActivity implements SyncInterface, MovieClickInterface {

    private RecyclerView searchList;
    private Toolbar toolbar;
    private TextView noResults;

    private List<MovieDetail> movieDetailList = new ArrayList<>();
    private String query;

    private MovieGenreAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SearchMoviesContract.Presenter presenter = new SerchMoviesPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie_activity);
        initViews();
    }

    @SuppressLint("RestrictedApi")
    private void initViews() {
        searchList = findViewById(R.id.search_list);
        noResults = findViewById(R.id.no_results);

        configToolbar();

        if (getIntent().getExtras() != null && getIntent().getExtras().getString("query") != null) {
            query = getIntent().getExtras().getString("query");
            requestSearchMovies();
        }
    }

    private void configToolbar() {
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        setUpSearchView(menu);

        return true;
    }

    private void requestSearchMovies() {
        presenter.RequestSearchMovies(this, query, movieDetailList);
    }

    @Override
    public void onSuccessSync() {
        verifyList();
    }

    @Override
    public void onFailureSync() {

    }

    private void setAdapter() {

        ArrayList<GenreAndMoviesResponse> moviesResponse = new ArrayList<>();

        moviesResponse.add(new GenreAndMoviesResponse(movieDetailList,
                getApplicationContext().getString(R.string.movies)));

        mAdapter = new MovieGenreAdapter(this, moviesResponse);
        mLayoutManager = new LinearLayoutManager(this);
        searchList.setLayoutManager(mLayoutManager);
        searchList.setAdapter(mAdapter);

    }

    private void verifyList() {
        if (movieDetailList.size() > 0) {
            setAdapter();
        } else {
            noResults.setText(getString(R.string.no_results, query));
            noResults.setVisibility(View.VISIBLE);
            searchList.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClickListener(MovieDetail movieDetail) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movieDetail", movieDetail);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}