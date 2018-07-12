package de.manayv.kotlinretrofitcountrysearch

import com.google.gson.annotations.SerializedName

// Improvements over Java: All related "JSON" data classes in 1 file. No getters/setters overhead

data class ResponseRoot(@SerializedName("RestResponse") val restResponse : RestResponse?)

data class RestResponse(val messages : ArrayList<String>?,
                        @SerializedName("result") val country : Country?)

data class Country(val name : String?,
                   @SerializedName("alpha2_code") val isoCode2 : String?,
                   @SerializedName("alpha3_code") val isoCode3 : String?)

/* Sample Response
{
  "RestResponse" : {
    "messages" : [ "Country found matching code [DE]." ],
    "result" : {
      "name" : "Germany",
      "alpha2_code" : "DE",
      "alpha3_code" : "DEU"
    }
  }
}
*/