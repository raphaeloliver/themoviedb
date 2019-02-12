package br.com.raphaeloliveira.themoviedb.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreResponse {

    @SerializedName("genres")
    private final List<Genre> genres;

    public GenreResponse(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }
}