package com.example.pataventura.di

import android.content.Context
import androidx.room.Room
import com.example.pataventura.data.database.PataVenturaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val PATA_VENTURA_DATA_BASE_NAME = "PataVenturaDatabase"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) :PataVenturaDatabase{
        //context.deleteDatabase(PATA_VENTURA_DATA_BASE_NAME)
        return Room.databaseBuilder(context, PataVenturaDatabase::class.java, PATA_VENTURA_DATA_BASE_NAME)
            .build()
    }
    @Singleton
    @Provides
    fun provideTokenDao(db: PataVenturaDatabase) = db.tokenDao()

    @Singleton
    @Provides
    fun provideTutorDao(db: PataVenturaDatabase) = db.tutorDao()

    @Singleton
    @Provides
    fun provideCuidadorDao(db: PataVenturaDatabase) = db.cuidadorDao()

    @Singleton
    @Provides
    fun provideServicioDao(db: PataVenturaDatabase) = db.servicioDao()

    @Singleton
    @Provides
    fun provideMascotaDao(db: PataVenturaDatabase) = db.mascotaDao()
}