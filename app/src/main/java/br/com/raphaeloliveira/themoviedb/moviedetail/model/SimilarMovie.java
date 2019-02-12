package br.com.raphaeloliveira.themoviedb.moviedetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SimilarMovie {

    @SerializedName("results")
    private ArrayList<MovieDetail> similarMovies;

    public ArrayList<MovieDetail> getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(ArrayList<MovieDetail> similarMovies) {
        this.similarMovies = similarMovies;
    }
}