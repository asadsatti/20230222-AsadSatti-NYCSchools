package com.example.nyc_school.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "api_remote_key")
data class ApiRemoteKey(
    @PrimaryKey
    @ColumnInfo(name = "api_name", collate = ColumnInfo.NOCASE)
    val apiName: String,
    @ColumnInfo(name = "api_next_page") val apiNextPage: Int)