package br.com.raphaeloliveira.themoviedb.moviedetail.presenter;

import base.api.SyncInterface;
import br.com.raphaeloliveira.themoviedb.moviedetail.contract.MovieDetailContract;
import br.com.raphaeloliveira.themoviedb.moviedetail.model.MovieDetail;
import br.com.raphaeloliveira.themoviedb.moviedetail.model.RequestSimilarMovies;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailPresenter implements MovieDetailContract.Presenter, SyncInterface {

    private MovieDetailContract.View view;
    private List<MovieDetail> movieDetailList = new ArrayList<>();

    @Override
    public void attachView(MovieDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void requestSimilar(MovieDetail movieDetail) {
        RequestSimilarMovies requestSimilarMovies = new RequestSimilarMovies(this,
                movieDetailList, movieDetail);
        requestSimilarMovies.startSync();
    }

    @Override
    public void onSuccessSync() {
        view.setAdapter(movieDetailList);
    }

    @Override
    public void onFailureSync() {

    }
}