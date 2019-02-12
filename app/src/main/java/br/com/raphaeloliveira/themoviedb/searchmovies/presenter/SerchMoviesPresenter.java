package br.com.raphaeloliveira.themoviedb.searchmovies.presenter;

import base.api.SyncInterface;
import br.com.raphaeloliveira.themoviedb.moviedetail.model.MovieDetail;
import br.com.raphaeloliveira.themoviedb.searchmovies.contract.SearchMoviesContract;
import br.com.raphaeloliveira.themoviedb.searchmovies.model.SearchMoviesModel;

import java.util.List;

public class SerchMoviesPresenter implements SearchMoviesContract.Presenter {

    private SearchMoviesModel model;

    private SearchMoviesContract.View view;

    @Override
    public void RequestSearchMovies(SyncInterface syncInterface, String query, List<MovieDetail> movieDetailList) {
        model = new SearchMoviesModel(syncInterface, query, movieDetailList);
        model.startSync();
    }

    @Override
    public void attachView(SearchMoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}