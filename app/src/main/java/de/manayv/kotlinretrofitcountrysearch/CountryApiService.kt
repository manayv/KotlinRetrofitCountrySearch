package de.manayv.kotlinretrofitcountrysearch

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApiService {

    // Sample request: http://services.groupkt.com/country/get/iso2code/DE
    // See {Iso2Code} in @GET and related @Path("Iso2Code"). No Query parms used

    // Returns a RxJava Single of the "Root JSON document" returned from the service
    @GET("/country/get/iso2code/{Iso2Code}")
    fun getForIso2Code(@Path("Iso2Code") iso2Code: String): Single<ResponseRoot>

    companion object {
        // It's not required, that fun create() is wrapped in a companion object.
        // It could be a to top-level fun too. In that case the fun name should reflect,
        // what it creates (e.g. fun createCountryApiService())
        fun create(): CountryApiService =

            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://services.groupkt.com")
                    .build()    // a Retrofit object
                    .create(CountryApiService::class.java)  // a CountryApiService impl.
    }
}
