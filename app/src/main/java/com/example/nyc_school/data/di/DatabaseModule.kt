package com.example.nyc_school.data.di

import android.content.Context
import androidx.room.Room
import com.example.nyc_school.data.db.SchoolDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DATABASE_NAME_NYC_SCHOOL = "nyc_school.db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesNycSchoolDatabase(
        @ApplicationContext context: Context,
    ): SchoolDB = Room.databaseBuilder(
        context,
        SchoolDB::class.java,
        DATABASE_NAME_NYC_SCHOOL,
    ).build()
}
