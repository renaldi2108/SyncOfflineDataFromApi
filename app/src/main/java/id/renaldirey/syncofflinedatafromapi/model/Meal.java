package id.renaldirey.syncofflinedatafromapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meal {
    @SerializedName("meals")
    List<ListMeal> data;

    public List<ListMeal> getData() {
        return data;
    }

    public void setData(List<ListMeal> data) {
        this.data = data;
    }
}