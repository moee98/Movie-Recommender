package model;


public class Ratings {
	
public Ratings(Integer userId, String movieId, float rating) {
	super();
	this.userId = userId;
	this.movieId = movieId;
	this.rating = rating;
}


Integer userId;
String movieId;
 float rating;

public Integer getUserId() {
	return userId; 
}

public void setUserId(Integer userId) {
	this.userId = userId;
}

public String getMovieId() {
	return movieId;
}

public void setMovieId(String movieId) {
	this.movieId = movieId;
}

public float getRatings() {
	return rating;
}

public void setRatings(float rating) {
	this.rating = rating;
}

public String toString() {
	return  movieId + " " + userId + " " + rating +"\n" ;
}

}