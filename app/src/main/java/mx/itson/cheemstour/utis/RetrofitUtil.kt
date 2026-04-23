package mx.itson.cheemstour.utis

import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory
import mx.itson.cheemstour.interfaces.CheemsAPI
import retrofit2.Retrofit

class RetrofitUtil {

    fun getApi() : CheemsAPI {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder().baseUrl("http://10.232.12.104:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

        return retrofit.create(CheemsAPI::class.java)
    }

}