package com.example.mytaxiservice.server;

import com.example.mytaxiservice.Register.StoreResponse;
import com.example.mytaxiservice.categories.CategoriesResponse;
import com.example.mytaxiservice.login.LoginResponse;
import com.example.mytaxiservice.tripAddress.RecentAddressResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @POST("api/users/login")
    Call<LoginResponse> loginData(@Query("email") String email,
                                  @Query("password") String password,
                                  @Query("role") String role);

    @GET("api/vehicles/categories")
    Call<CategoriesResponse> categoriesData(@Header("Authorization") String token);

    @Multipart
    @POST("api/users/store")
    Call<StoreResponse> storeData

            (
                    @Part("name") RequestBody name,
                    @Part("email") RequestBody email,
                    @Part("mobile") RequestBody mobile,
                    @Part("password") RequestBody password,
                    @Part("status") RequestBody status,
                    @Part("role") RequestBody role,
                    @Part("user_type") RequestBody userType,
                    @Part("company_name") RequestBody companyName,
                    @Part("country_code") RequestBody countryCode,
                    @Part("device_type") RequestBody deviceType,
                    @Part MultipartBody.Part profileImg

            );

    @GET("api/rides/locations/history")
    Call<RecentAddressResponse> fetchRecentAddress(@Header("Authorization") String token);
}
