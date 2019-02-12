package br.com.raphaeloliveira.themoviedb.home.model;

import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    public Genre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}