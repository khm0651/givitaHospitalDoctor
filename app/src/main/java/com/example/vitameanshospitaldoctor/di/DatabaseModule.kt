package com.example.vitameanshospitaldoctor.di

import android.content.Context
import com.example.vitameanshospitaldoctor.data.AppDatabase
import com.example.vitameanshospitaldoctor.data.dao.bloodPressureDataDao
import com.example.vitameanshospitaldoctor.data.dao.bloodSugarDataDao
import com.example.vitameanshospitaldoctor.data.dao.userDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    fun provideUserDataDao(appDatabase: AppDatabase): userDataDao = appDatabase.userDataDao()

    @Provides
    fun provideBloodSugarDataDao(appDatabase: AppDatabase): bloodSugarDataDao = appDatabase.bloodSugarDataDao()

    @Provides
    fun provideBloodPressureDataDao(appDatabase: AppDatabase): bloodPressureDataDao = appDatabase.bloodPressureDataDao()

}