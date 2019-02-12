package br.com.raphaeloliveira.themoviedb.home.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import base.baseactivity.BaseActivity;
import br.com.raphaeloliveira.themoviedb.R;
import br.com.raphaeloliveira.themoviedb.home.contract.MovieGenreContract;
import br.com.raphaeloliveira.themoviedb.home.model.GenreAndMoviesResponse;
import br.com.raphaeloliveira.themoviedb.home.presenter.MovieGenrePresenter;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.movies_category_list)
public class MovieGenreActivity extends BaseActivity implements MovieGenreContract.View {

    private MovieGenreContract.Presenter presenter = new MovieGenrePresenter();
    private MovieGenreAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @ViewById(R.id.category_list)
    RecyclerView categoryList;

    @ViewById(R.id.progress)
    ProgressBar progressBar;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        requestGenres();
    }

    private void initViews() {
        presenter.attachView(this);
        configToolbar();
    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
    }

    private void requestGenres() {
        presenter.requestGenres();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setAdapter(ArrayList<GenreAndMoviesResponse> genreAndMovieList) {
        mAdapter = new MovieGenreAdapter(this, genreAndMovieList);
        mLayoutManager = new LinearLayoutManager(this);
        categoryList.setLayoutManager(mLayoutManager);
        categoryList.setAdapter(mAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        setUpSearchView(menu);
        return true;
    }
}