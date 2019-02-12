package br.com.raphaeloliveira.themoviedb.home.presenter;

import base.api.SyncInterface;
import br.com.raphaeloliveira.themoviedb.home.contract.MovieGenreContract;
import br.com.raphaeloliveira.themoviedb.home.model.Genre;
import br.com.raphaeloliveira.themoviedb.home.model.GenreAndMoviesResponse;
import br.com.raphaeloliveira.themoviedb.home.model.GenreRequest;
import br.com.raphaeloliveira.themoviedb.home.model.RequestMoviesByCategory;

import java.util.ArrayList;
import java.util.List;

public class MovieGenrePresenter implements MovieGenreContract.Presenter, SyncInterface,
        RequestMoviesByCategory.RequestMoviesListener {

    private MovieGenreContract.View view;

    private GenreRequest genreRequest;
    private RequestMoviesByCategory requestMoviesByCategory;

    public List<Genre> genreList = new ArrayList<>();
    private ArrayList<GenreAndMoviesResponse> genreAndMovieList = new ArrayList<>();

    private int count = 0;

    @Override
    public void attachView(MovieGenreContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void requestGenres() {
        genreRequest = new GenreRequest(this, genreList);
        genreRequest.startSync();
    }

    @Override
    public void requestMoviesByCategory() {
        if (genreList != null && genreList.size() > 0) {
            requestMoviesByCategory = new RequestMoviesByCategory(this,
                    genreList.get(count), genreAndMovieList);
            requestMoviesByCategory.startSync();
        }
    }

    @Override
    public void onSuccessSync() {
        requestMoviesByCategory();
    }

    @Override
    public void onFailureSync() {

    }

    @Override
    public void success() {
        count++;
        if (count < genreList.size()) {
            requestMoviesByCategory();
        } else {
            view.setAdapter(genreAndMovieList);
        }
    }

    @Override
    public void failure() {

    }
}