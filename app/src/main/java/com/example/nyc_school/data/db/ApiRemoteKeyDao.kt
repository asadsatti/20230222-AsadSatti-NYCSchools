package com.example.nyc_school.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao interface ApiRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keys: ApiRemoteKey)

    @Query("SELECT * FROM api_remote_key WHERE api_name = :apiName")
    suspend fun remoteKeyByApiName(apiName: String): ApiRemoteKey

    @Query("DELETE FROM api_remote_key WHERE api_name = :apiName")
    suspend fun deleteByApiName(apiName: String)
}