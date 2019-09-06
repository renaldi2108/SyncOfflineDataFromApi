package id.renaldirey.syncofflinedatafromapi.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;

import id.renaldirey.syncofflinedatafromapi.model.ListMeal;

public class MealConverter implements Serializable {

    @TypeConverter
    public String fromItemValue(ListMeal item) {
        if (item == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ListMeal>() {
        }.getType();

        String json = gson.toJson(item, type);
        return json;
    }

    @TypeConverter
    public ListMeal toItemValue(String itemValueString) {
        if (itemValueString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ListMeal>() {
        }.getType();
        ListMeal item = gson.fromJson(itemValueString, type);
        return item;
    }
}
