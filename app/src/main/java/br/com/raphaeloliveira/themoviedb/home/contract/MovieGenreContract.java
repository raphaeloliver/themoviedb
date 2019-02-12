package br.com.raphaeloliveira.themoviedb.home.contract;

import base.basecontract.BaseContract;
import br.com.raphaeloliveira.themoviedb.home.model.GenreAndMoviesResponse;

import java.util.ArrayList;

public class MovieGenreContract {

    public interface View extends BaseContract.View {

        void setAdapter(ArrayList<GenreAndMoviesResponse> genreAndMovieList);
    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void requestGenres();

        void requestMoviesByCategory();
    }
}