package com.example.nyc_school.data.db

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface SchoolDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(schools: List<School>)

    @Query("SELECT * FROM school")
    fun getAllSchools(): PagingSource<Int, School>

    @Query("DELETE FROM school")
    suspend fun deleteAllSchools()

    @Query("SELECT COUNT(dbn) FROM school")
    suspend fun getRowCount(): Int
}