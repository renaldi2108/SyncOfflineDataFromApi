package id.renaldirey.syncofflinedatafromapi.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import id.renaldirey.syncofflinedatafromapi.database.dao.MealDao;
import id.renaldirey.syncofflinedatafromapi.model.ListMeal;
import id.renaldirey.syncofflinedatafromapi.utils.Constant;

@Database(entities = {ListMeal.class}, version = Constant.DATABASE_VERSION, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase INSTANCE;
    public abstract MealDao mealDao();

    public static MyDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class, "meals-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
