package model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingsResult {

@SerializedName("Ratings")
@Expose
private List<Ratings> ratings = null;

public List<Ratings> getRatings() {
return ratings;
}

public void setRatings(List<Ratings> ratings) {
this.ratings = ratings;
}

}
