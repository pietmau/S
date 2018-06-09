package com.pppp.s.main.api

import com.pppp.s.main.model.pokos.MovieResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/** Not cached */
class RetrofitApi(baseUrl: String, apiTimeOutInSeconds: Long = 60) : Api {
    private val api: Api

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .readTimeout(apiTimeOutInSeconds, TimeUnit.SECONDS)
            .connectTimeout(apiTimeOutInSeconds, TimeUnit.SECONDS)
            .cache(null)//Remove cache here because the model does the caching
            .addInterceptor(interceptor)
            .build()

        api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Api::class.java)
    }

    override fun getMovies(): Observable<MovieResponse> = api.getMovies()
}
