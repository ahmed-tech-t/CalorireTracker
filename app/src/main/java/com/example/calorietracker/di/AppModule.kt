package com.example.calorietracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.core.domain.DefaultPreferences
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.use_case.FilterOutDigit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideSharedPreference(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("app_shared", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPref: SharedPreferences): Preferences {
        return DefaultPreferences(sharedPref)
    }

    @Provides
    @Singleton
    fun provideFilterOutDigit(): FilterOutDigit = FilterOutDigit()
}