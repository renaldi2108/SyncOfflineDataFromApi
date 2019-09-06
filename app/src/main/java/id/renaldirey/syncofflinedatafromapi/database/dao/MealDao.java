package id.renaldirey.syncofflinedatafromapi.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import id.renaldirey.syncofflinedatafromapi.database.converter.MealConverter;
import id.renaldirey.syncofflinedatafromapi.database.entity.MealEntity;
import id.renaldirey.syncofflinedatafromapi.model.ListMeal;

@Dao
public interface MealDao {
    @TypeConverters(MealConverter.class)
    @Query("SELECT * FROM meals WHERE item = :item")
    MealEntity getDataWhere(ListMeal item);

    @Insert
    void insertAll(MealEntity... data);

    @Delete
    void deleteMeal(MealEntity data);
}
