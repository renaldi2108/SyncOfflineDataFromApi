package id.renaldirey.syncofflinedatafromapi.database;

import java.util.List;

import id.renaldirey.syncofflinedatafromapi.model.ListMeal;

public class DatabaseConfig {

    public static void insertData(MyDatabase db, ListMeal data) {
        db.mealDao().insertAll(data);
    }

    public static void deleteMealFrom(MyDatabase db, ListMeal data) {
        db.mealDao().deleteMeal(data);
    }

    public static ListMeal getDataCurriculumWhere(MyDatabase db, String idMeal) {
        return db.mealDao().getDataWhere(idMeal);
    }

    public static List<ListMeal> getData(MyDatabase db) {
        return db.mealDao().getData();
    }
}
