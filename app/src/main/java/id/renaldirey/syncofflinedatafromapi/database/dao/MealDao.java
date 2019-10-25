package id.renaldirey.syncofflinedatafromapi.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import id.renaldirey.syncofflinedatafromapi.model.ListMeal;

@Dao
public interface MealDao {
    @Query("SELECT * FROM meals")
    List<ListMeal> getData();

    @Query("SELECT * FROM meals WHERE idMeal = :idMeal")
    ListMeal getDataWhere(String idMeal);

    @Insert
    void insertAll(ListMeal... data);

    @Delete
    void deleteMeal(ListMeal data);
}
