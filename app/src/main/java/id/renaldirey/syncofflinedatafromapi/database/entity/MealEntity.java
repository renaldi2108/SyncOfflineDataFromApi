package id.renaldirey.syncofflinedatafromapi.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import id.renaldirey.syncofflinedatafromapi.database.converter.MealConverter;
import id.renaldirey.syncofflinedatafromapi.model.ListMeal;

@Entity(tableName = "meals")
public class MealEntity {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @TypeConverters(MealConverter.class)
    @ColumnInfo(name = "item")
    ListMeal item;

    public MealEntity(ListMeal item) {
        this.item = item;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public ListMeal getItem() {
        return item;
    }

    public void setItem(ListMeal item) {
        this.item = item;
    }
}
