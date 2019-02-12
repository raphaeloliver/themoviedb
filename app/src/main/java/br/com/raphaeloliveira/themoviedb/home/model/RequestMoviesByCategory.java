package br.com.raphaeloliveira.themoviedb.home.model;

import base.api.ApiUtils;
import base.api.BaseSync;
import base.api.RetrofitConfig;
import base.api.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class RequestMoviesByCategory extends BaseSync {

    public interface RequestMoviesListener {
        void success();

        void failure();
    }

    private RequestMoviesListener requestMoviesListener;
    private List<GenreAndMoviesResponse> genreAndMoviesList;

    private Call<GenreAndMoviesResponse> call;

    private Genre genre;


    public RequestMoviesByCategory(RequestMoviesListener requestMoviesListener, Genre genre,
                                   List<GenreAndMoviesResponse> genreAndMoviesList) {
        this.requestMoviesListener = requestMoviesListener;
        this.genre = genre;
        this.genreAndMoviesList = genreAndMoviesList;
    }

    @Override
    public void onSuccessSync() {
        requestMoviesListener.success();
    }

    @Override
    public void onFailureSync() {
        requestMoviesListener.failure();
    }

    @Override
    public void startSync() {
        ServiceApi serviceApi = RetrofitConfig.getService();

        call = serviceApi.getMoviesByGenre(genre.getId(), ApiUtils.API_KEY, ApiUtils.API_LANGUAGE,
                "true", "created_at.asc");

        call.enqueue(new Callback<GenreAndMoviesResponse>() {
            @Override
            public void onResponse(Call<GenreAndMoviesResponse> call, Response<GenreAndMoviesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GenreAndMoviesResponse genreAndMovie = response.body();

                    if (genreAndMovie != null) {
                        genreAndMovie.setCategoryName(genre.getName());
                        genreAndMoviesList.add(genreAndMovie);
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