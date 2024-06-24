package com.viesure.assigment.database.databasemodule

import android.content.Context
import androidx.room.Room
import com.viesure.assigment.database.AppDatabase
import com.viesure.assigment.database.dao.BooksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "viesure_database"
        )
            .build()
    }

    @Provides
    fun provideBooksDao(database: AppDatabase): BooksDao {
        return database.booksDao()
    }
}