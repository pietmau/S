package com.pppp.s.main.api

import com.pppp.s.main.model.pokos.MovieResponse
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/** Cached */
class RetrofitApi(// TODO encapsulate in Network class and replace
    baseUrl: String,
    apiTimeOutInSeconds: Long = 60,
    dir: File
) : Api {
    private val api: Api

    init {
        val logger = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .readTimeout(apiTimeOutInSeconds, TimeUnit.SECONDS)
            .connectTimeout(apiTimeOutInSeconds, TimeUnit.SECONDS)
            .cache(Cache(dir, 10 * 1024 * 1024))
            .addInterceptor(logger)
            .addNetworkInterceptor(TimedInterceptor())
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

    override fun getMoviesSync(): Call<MovieResponse> = api.getMoviesSync()
}


