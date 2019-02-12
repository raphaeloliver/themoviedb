package br.com.raphaeloliveira.themoviedb.home.model;

import base.api.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class GenreRequest extends BaseSync {

    private Call<GenreResponse> repo;
    private SyncInterface syncInterface;
    private List<Genre> genreList;

    public GenreRequest(SyncInterface syncInterface, List<Genre> genreList) {
        this.syncInterface = syncInterface;
        this.genreList = genreList;
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

        repo = serviceApi.getGenre(ApiUtils.API_KEY, ApiUtils.API_LANGUAGE);

        repo.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GenreResponse genreResponse = response.body();

                    if (genreResponse != null) {
                        genreList.addAll(genreResponse.getGenres());
                    }

                    onSuccessSync();

                } else {
                    onFailureSync();
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                onFailureSync();
            }
        });
    }
}