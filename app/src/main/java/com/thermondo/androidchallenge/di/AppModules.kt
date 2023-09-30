package com.thermondo.androidchallenge.di

import android.content.Context
import androidx.room.Room
import com.thermondo.androidchallenge.database.LaunchRoomDatabase
import com.thermondo.androidchallenge.repository.LaunchRepository
import com.thermondo.androidchallenge.repository.SpaceXRepository
import com.thermondo.androidchallenge.service.AuthInterceptor
import com.thermondo.androidchallenge.service.SpaceXApiService
import com.thermondo.androidchallenge.viewmodel.SpaceXViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
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
    factory { LaunchRepository(get()) }
}

val persistenceModule = module {
    single {provideDatabase(androidApplication())}

    single { get<LaunchRoomDatabase>().launchesDao() }
}

val viewModelModule = module {
    viewModel { SpaceXViewModel(get(), get()) }
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

fun provideDatabase(androidContext: Context): LaunchRoomDatabase {
    return Room.databaseBuilder(
        androidContext,
        LaunchRoomDatabase::class.java,
        "launch_database",
    ).fallbackToDestructiveMigration()
        .build()
}
