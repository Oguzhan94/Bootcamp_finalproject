package com.example.bootcampfinalproject.di

import android.content.Context
import androidx.room.Room
import com.example.bootcampfinalproject.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = "bookmark_database"
    ).build()

    @Provides
    fun provideDao(database: AppDatabase) = database.bookmarkDao()
}
