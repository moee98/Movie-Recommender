package model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResult {

@SerializedName("Movies")
@Expose
private List<Movies> movies = null;

public List<Movies> getMovie() {
return movies;
}

public void setMovie(List<Movies> movies) {
this.movies = movies;
}

}
