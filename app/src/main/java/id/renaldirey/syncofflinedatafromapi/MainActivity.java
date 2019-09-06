package id.renaldirey.syncofflinedatafromapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.renaldirey.syncofflinedatafromapi.adapter.MealAdapter;
import id.renaldirey.syncofflinedatafromapi.model.Meal;
import id.renaldirey.syncofflinedatafromapi.network.ServiceGenerator;
import id.renaldirey.syncofflinedatafromapi.network.service.MyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.rv_meal) RecyclerView rvMeal;

    MyService apiService;
    MealAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MealAdapter(this);
        rvMeal.setLayoutManager(new LinearLayoutManager(this));
        rvMeal.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        apiService = ServiceGenerator.createService(this, MyService.class);
        Call<Meal> callData = apiService.data("Dessert");
        callData.enqueue(new Callback<Meal>() {
            @Override
            public void onResponse(Call<Meal> call, Response<Meal> response) {
                if(response.code() == 200)
                    if(response.body().getData()!=null)
                        adapter.addAll(response.body().getData());
            }

            @Override
            public void onFailure(Call<Meal> call, Throwable t) {
                Log.e(TAG+".error", t.toString());
            }
        });
    }
}
