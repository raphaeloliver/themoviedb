package br.com.raphaeloliveira.themoviedb.home.model;

import br.com.raphaeloliveira.themoviedb.moviedetail.model.MovieDetail;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreAndMoviesResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<MovieDetail> results;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;

    private String categoryName;

    public GenreAndMoviesResponse(int page, List<MovieDetail> results, int totalPages, int totalResults, String categoryName) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
        this.categoryName = categoryName;
    }

    public GenreAndMoviesResponse(List<MovieDetail> results, String categoryName) {
        this.results = results;
        this.categoryName = categoryName;
    }

    public int getPage() {
        return page;
    }

    public List<MovieDetail> getResults() {
        return results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}