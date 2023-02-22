package com.example.nyc_school.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [School::class, ApiRemoteKey::class], version = 1)
abstract class SchoolDB : RoomDatabase() {
    abstract fun schoolDao(): SchoolDao
    abstract fun apiRemoteKeyDao(): ApiRemoteKeyDao
}