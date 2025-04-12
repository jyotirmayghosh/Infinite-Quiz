package com.jyotirmay.infinitequiz

import android.app.Application
import androidx.room.Room
import com.jyotirmay.infinitequiz.data.local.AppDatabase
import com.jyotirmay.infinitequiz.data.local.QuizDao
import com.jyotirmay.infinitequiz.data.remote.CountryApiService
import com.jyotirmay.infinitequiz.data.remote.QuizApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    @Provides
    @Singleton
    fun provideCountryAPIInstance(): CountryApiService {
        return Retrofit
            .Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuizAPIInstance(): QuizApiService {
        return Retrofit
            .Builder()
            .baseUrl("https://6789df4ddd587da7ac27e4c2.mockapi.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = AppDatabase::class.java,
            name = "quiz_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase): QuizDao = appDatabase.quizDao

}