package com.thermondo.androidchallenge.di

import com.thermondo.androidchallenge.repository.SpaceXRepository
import com.thermondo.androidchallenge.service.AuthInterceptor
import com.thermondo.androidchallenge.service.SpaceXApiService
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideSpaceXApi(get()) }
    single { provideRetrofit(get()) }
}

val serviceModule = module {
    factory { SpaceXRepository(get()) }
}

val viewModelModule = module {
    viewModel { SpaceXViewModel(get()) }
}

private const val API_URL = "https://api.spacexdata.com/v5/"

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
    return OkHttpClient().newBuilder().addInterceptor(logging).addInterceptor(authInterceptor).build()
}

fun provideSpaceXApi(retrofit: Retrofit): SpaceXApiService = retrofit.create(SpaceXApiService::class.java)
