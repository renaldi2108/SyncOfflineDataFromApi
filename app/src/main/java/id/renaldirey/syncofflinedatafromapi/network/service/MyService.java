package id.renaldirey.syncofflinedatafromapi.network.service;

import id.renaldirey.syncofflinedatafromapi.model.Meal;
import id.renaldirey.syncofflinedatafromapi.utils.Constant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyService {
    @GET(Constant.Endpoint.API_DESSERT)
    Call<Meal> data(@Query("c") String filter);
}
