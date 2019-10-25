package id.renaldirey.syncofflinedatafromapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.renaldirey.syncofflinedatafromapi.adapter.MealAdapter;
import id.renaldirey.syncofflinedatafromapi.database.DatabaseConfig;
import id.renaldirey.syncofflinedatafromapi.database.MyDatabase;
import id.renaldirey.syncofflinedatafromapi.listener.AddClickListener;
import id.renaldirey.syncofflinedatafromapi.model.Meal;
import id.renaldirey.syncofflinedatafromapi.network.ServiceGenerator;
import id.renaldirey.syncofflinedatafromapi.network.service.MyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AddClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.rv_meal) RecyclerView rvMeal;

    private MyService apiService;
    private MealAdapter adapter;

    private boolean isOnline = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MealAdapter(this);
        adapter.setAddClickListener(this);
        rvMeal.setLayoutManager(new LinearLayoutManager(this));
        rvMeal.setAdapter(adapter);

        isOnline = isConnected(this);

        if(isOnline)
            loadData();
        else
            loadOfflineData();

    }

    private boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void loadOfflineData() {
        if(DatabaseConfig.getData(MyDatabase.getInstance(this)) != null)
            adapter.addAll(DatabaseConfig.getData(MyDatabase.getInstance(this)));
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

    @Override
    public void onAddClick(boolean isAvailable, int position) {
        if(!isAvailable) {
            Toast.makeText(this, "Tambahin nih", Toast.LENGTH_SHORT).show();
            DatabaseConfig.insertData(MyDatabase.getInstance(this), adapter.getData(position));
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Hapus nih", Toast.LENGTH_SHORT).show();
            DatabaseConfig.deleteMealFrom(MyDatabase.getInstance(this), DatabaseConfig.getDataCurriculumWhere(MyDatabase.getInstance(this), adapter.getData(position).getId()));

            if(!isOnline)
                adapter.remove(position);

            adapter.notifyDataSetChanged();
        }
    }
}
