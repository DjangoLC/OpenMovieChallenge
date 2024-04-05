package com.example.openmovie.di

import android.content.Context
import com.example.openmovie.data.datasource.local.AppDataBase
import com.example.openmovie.data.datasource.local.LocalDataSource
import com.example.openmovie.data.datasource.local.LocalDataSourceImpl
import com.example.openmovie.data.datasource.remote.FirestoreRepository
import com.example.openmovie.data.datasource.remote.ProfileRepository
import com.example.openmovie.data.datasource.remote.RemoteDataSource
import com.example.openmovie.data.datasource.remote.RemoteDataSourceImpl
import com.example.openmovie.location.LocationClient
import com.example.openmovie.location.LocationClientImpl
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return AppDataBase.createInstance(appContext)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(db: AppDataBase): LocalDataSource {
        return LocalDataSourceImpl(db)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideFirestoreRepository(): FirestoreRepository {
        return FirestoreRepository()
    }

    @Provides
    @Singleton
    fun provideLocationClient(@ApplicationContext appContext: Context): LocationClient {
        return LocationClientImpl(
            appContext,
            LocationServices.getFusedLocationProviderClient(appContext)
        )
    }

    @Provides
    @Singleton
    fun provideProfileRepository(): ProfileRepository {
        return ProfileRepository()
    }
}

