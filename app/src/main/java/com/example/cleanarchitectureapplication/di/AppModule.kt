package com.example.cleanarchitectureapplication.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import coil.ImageLoader
import com.example.cleanarchitectureapplication.data.datasourse.CountryDataSourceImpl
import com.example.cleanarchitectureapplication.domain.dataSource.CountryDataSource
import com.example.cleanarchitectureapplication.data.local.AppDatabase
import com.example.cleanarchitectureapplication.data.local.CountryDao
import com.example.cleanarchitectureapplication.data.remote.CountriesApi
import com.example.cleanarchitectureapplication.util.NetworkUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideImageLoader(app: Application): ImageLoader {
        return ImageLoader(app)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
    }

    @Provides
    @Singleton
    fun providesCountriesApi(client: OkHttpClient, gson: Gson): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(CountriesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java,
            "myDatabase.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideNetworkUtil(context: Context): NetworkUtil = NetworkUtil(context)

    @Provides
    @Singleton
    fun provideCountryDao(appDatabase: AppDatabase): CountryDao {
        return appDatabase.countryDao()
    }

    @Provides
    @Singleton
    fun provideCountryDataSource(countryDao: CountryDao): CountryDataSource {
        return CountryDataSourceImpl(countryDao)
    }
}
