package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movies {

	public Movies(int movieId, String title, String genres) {
		super();
		this.movieId = movieId;
		this.genres = genres;
		this.title = title;
	}

	@SerializedName("movieId")
	@Expose
	int movieId;
	String genres;
	String title;

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String toString() {
		return movieId + " " + title + " " + genres + "\n";
	}
}