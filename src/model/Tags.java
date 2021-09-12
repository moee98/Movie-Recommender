package model;

public class Tags {
	
public Tags(int userId, int movieId, String tags) {
	super();
	this.userId = userId;
	this.movieId = movieId;
	this.tags = tags;
}


int userId;
int movieId;
 String tags;

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public int getMovieId() {
	return movieId;
}

public void setMovieId(int movieId) {
	this.movieId = movieId;
}

public String getTags() {
	return tags;
}

public void setTags(String tags) {
	this.tags = tags;
}

public String toString() {
	return  movieId + " " + userId + " " + tags +"\n" ;
}

}