package id.renaldirey.syncofflinedatafromapi.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "meals")
public class ListMeal {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "idMeal")
    @SerializedName("idMeal")
    String id;
    @ColumnInfo(name = "strMeal")
    @SerializedName("strMeal")
    String name;
    @ColumnInfo(name = "strMealThumb")
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
