package com.example.vitameanshospitaldoctor.di

import android.content.Context
import com.example.vitameanshospitaldoctor.data.AppDatabase
import com.example.vitameanshospitaldoctor.data.dao.BloodPressureDataDao
import com.example.vitameanshospitaldoctor.data.dao.BloodSugarDataDao
import com.example.vitameanshospitaldoctor.data.dao.UserDataDao
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
    fun provideUserDataDao(appDatabase: AppDatabase): UserDataDao = appDatabase.userDataDao()

    @Provides
    fun provideBloodSugarDataDao(appDatabase: AppDatabase): BloodSugarDataDao = appDatabase.bloodSugarDataDao()

    @Provides
    fun provideBloodPressureDataDao(appDatabase: AppDatabase): BloodPressureDataDao = appDatabase.bloodPressureDataDao()

}