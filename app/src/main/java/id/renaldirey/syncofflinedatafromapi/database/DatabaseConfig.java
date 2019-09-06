package id.renaldirey.syncofflinedatafromapi.database;

import id.renaldirey.syncofflinedatafromapi.database.entity.MealEntity;
import id.renaldirey.syncofflinedatafromapi.model.ListMeal;

public class DatabaseConfig {
    public static final String TAG = DatabaseConfig.class.getSimpleName();

    public static void insertData(MyDatabase db, MealEntity data) {
        db.mealDao().insertAll(data);
    }

    public static void deleteMealFrom(MyDatabase db, MealEntity data) {
        db.mealDao().deleteMeal(data);
    }

    public static MealEntity getDataCurriculumWhere(MyDatabase db, ListMeal data) {
        return db.mealDao().getDataWhere(data);
    }
}
