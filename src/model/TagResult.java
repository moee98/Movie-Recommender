package model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagResult {

@SerializedName("Tags")
@Expose
private List<Tags> tags = null;

public List<Tags> getTags() {
return tags;
}

public void setTags(List<Tags> tags) {
this.tags = tags;
}

}
