package br.com.raphaeloliveira.themoviedb.moviedetail.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import base.Utils;
import br.com.raphaeloliveira.themoviedb.R;
import br.com.raphaeloliveira.themoviedb.moviedetail.model.MovieDetail;

import java.util.List;

public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MyViewHolderDetail> {

    private Context context;

    private List<MovieDetail> movieDetailList;

    private MovieClickInterface movieClickInterface;

    public MovieDetailAdapter(Context context, List<MovieDetail> movieDetailList, MovieClickInterface movieClickInterface) {
        this.context = context;
        this.movieDetailList = movieDetailList;
        this.movieClickInterface = movieClickInterface;
    }

    @Override
    public MyViewHolderDetail onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_view_movie, parent, false);

        MyViewHolderDetail holder = new MyViewHolderDetail(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolderDetail holder, final int position) {
        final MovieDetail movieDetail = movieDetailList.get(position);
        holder.movieTitle.setText(movieDetail.getTitle());
        holder.movieRate.setText(String.valueOf(movieDetail.getRate()));

        Utils.loadImage(context, movieDetail.getPosterPath(), holder.imageMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieClickInterface.onItemClickListener(movieDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieDetailList != null ? movieDetailList.size() : 0;
    }

    class MyViewHolderDetail extends RecyclerView.ViewHolder {
        ImageView imageMovie;
        TextView movieTitle;
        TextView movieRate;

        public MyViewHolderDetail(View itemView) {
            super(itemView);
            imageMovie = itemView.findViewById(R.id.image_movie);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieRate = itemView.findViewById(R.id.movie_rate);
        }
    }
}