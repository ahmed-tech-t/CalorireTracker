package com.example.onboarding_domain.di

import com.example.onboarding_domain.use_case.NutrientGoalsValidation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class OnBoardingModules {

    @Provides
    @ViewModelScoped
    fun provideNutrientGoalsValidation(): NutrientGoalsValidation = NutrientGoalsValidation()
}