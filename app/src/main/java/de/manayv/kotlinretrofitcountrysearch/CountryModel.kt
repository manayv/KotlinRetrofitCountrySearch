package de.manayv.kotlinretrofitcountrysearch

import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("RestResponse") val restResponse : RestResponse?)
data class RestResponse(val messages : ArrayList<String>?,
                        @SerializedName("result") val country : Country?)
data class Country(val name : String?,
                   @SerializedName("alpha2_code") val isoCode2 : String?,
                   @SerializedName("alpha3_code") val isoCode3 : String?)
