package de.manayv.kotlinretrofitcountrysearch

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiService {

    @GET("/country/get/iso2code/{Iso2Code}")
    fun getForIso2Code(@Path("Iso2Code") iso2Code: String): Single<Response>


    companion object {
        fun create(): CountryApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://services.groupkt.com")
                    .build()

            return retrofit.create(CountryApiService::class.java)
        }
    }
}
