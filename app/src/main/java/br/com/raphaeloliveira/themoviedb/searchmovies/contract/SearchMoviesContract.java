package br.com.raphaeloliveira.themoviedb.searchmovies.contract;

import base.api.SyncInterface;
import base.basecontract.BaseContract;
import br.com.raphaeloliveira.themoviedb.moviedetail.model.MovieDetail;

import java.util.List;

public class SearchMoviesContract {
    public interface View extends BaseContract.View {
    }

    public interface Presenter extends BaseContract.Presenter<View> {
        void RequestSearchMovies(SyncInterface syncInterface, String query, List<MovieDetail> movieDetailList);
    }
}