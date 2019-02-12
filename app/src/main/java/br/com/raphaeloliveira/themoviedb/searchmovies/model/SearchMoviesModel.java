package br.com.raphaeloliveira.themoviedb.searchmovies.model;

import base.api.*;
import br.com.raphaeloliveira.themoviedb.home.model.GenreAndMoviesResponse;
import br.com.raphaeloliveira.themoviedb.moviedetail.model.MovieDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class SearchMoviesModel extends BaseSync {

    private SyncInterface syncInterface;
    private Call<GenreAndMoviesResponse> call;
    private String query;
    private List<MovieDetail> movieDetailList;

    public SearchMoviesModel(SyncInterface syncInterface, String query, List<MovieDetail> movieDetailList) {
        this.syncInterface = syncInterface;
        this.query = query;
        this.movieDetailList = movieDetailList;
    }

    @Override
    public void onSuccessSync() {
        syncInterface.onSuccessSync();
    }

    @Override
    public void onFailureSync() {
        syncInterface.onFailureSync();
    }

    @Override
    public void startSync() {

        ServiceApi serviceApi = RetrofitConfig.getService();

        call = serviceApi.searchMovie(ApiUtils.API_KEY, ApiUtils.API_LANGUAGE, "true", query);

        call.enqueue(new Callback<GenreAndMoviesResponse>() {
            @Override
            public void onResponse(Call<GenreAndMoviesResponse> call, Response<GenreAndMoviesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GenreAndMoviesResponse genreAndMoviesResponse = response.body();
                    if (genreAndMoviesResponse != null) {
                        movieDetailList.addAll(genreAndMoviesResponse.getResults());
                    }
                    onSuccessSync();
                } else {
                    onFailureSync();
                }
            }

            @Override
            public void onFailure(Call<GenreAndMoviesResponse> call, Throwable t) {
                onFailureSync();
            }
        });
    }
}