package com.yakymovych.simon.everywhere.di

import android.app.Application
import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.yakymovych.simon.everywhere.BASE_URL
import com.yakymovych.simon.everywhere.HOSTNAME
import com.yakymovych.simon.everywhere.MVVMApplication
import com.yakymovych.simon.everywhere.R
import com.yakymovych.simon.everywhere.data.RetroService
import com.yakymovych.simon.everywhere.utils.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: MVVMApplication): Context =application

    @Provides
    @Singleton
    open fun provideSchedulerProvider() = SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread())


    @Provides
    @Singleton
    internal open fun provideRetroService(context: Context): RetroService {
        return if (false) { //(BuildConfig.DEBUG) {
            throw NotImplementedError()
            //RetroServiceStub();
        } else {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val headerInterceptor = Interceptor() {
                val original = it.request()
                if (!original.url().toString().contains("")) {
                    val request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .method(original.method(), original.body())
                            .build()


                    it.proceed(request)
                } else {

                    it.proceed(original)
                }

            }

            val client = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(headerInterceptor)
                    .build()

            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(RetroService::class.java)
        }
    }
}