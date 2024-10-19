package com.example.cleanarchitectureapplication.di

import com.example.cleanarchitectureapplication.data.repository.CountryRepositoryImpl
import com.example.cleanarchitectureapplication.domain.repository.CountryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCountryRepository(
        repositoryImpl: CountryRepositoryImpl
    ): CountryRepository
}
