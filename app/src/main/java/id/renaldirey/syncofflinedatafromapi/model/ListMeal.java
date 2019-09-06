package id.renaldirey.syncofflinedatafromapi.model;

import com.google.gson.annotations.SerializedName;

public class ListMeal {
    @SerializedName("idMeal")
    String id;
    @SerializedName("strMeal")
    String name;
    @SerializedName("strMealThumb")
    String thumbnail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
