package base.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static final String URL_BASE = "https://api.themoviedb.org";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getInstance() {
        return retrofit;
    }

    public static ServiceApi getService(){

        retrofit = getInstance();
        return retrofit.create(ServiceApi.class);
    }
}