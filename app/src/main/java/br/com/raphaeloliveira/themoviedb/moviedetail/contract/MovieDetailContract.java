package br.com.raphaeloliveira.themoviedb.moviedetail.contract;

import base.basecontract.BaseContract;
import br.com.raphaeloliveira.themoviedb.moviedetail.model.MovieDetail;

import java.util.List;

public class MovieDetailContract {

    public interface View extends BaseContract.View {

        void setAdapter(List<MovieDetail> movieDetailList);
    }

    public interface Presenter extends BaseContract.Presenter<View> {
        void requestSimilar(MovieDetail movieDetail);
    }
}