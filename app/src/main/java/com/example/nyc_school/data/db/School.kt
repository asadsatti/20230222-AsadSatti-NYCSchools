package com.example.nyc_school.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "school")
data class School(
    @PrimaryKey
    @SerializedName("dbn") val dbn: String,

    @ColumnInfo(name = "city") val city: String?,
    @SerializedName("school_name")
    @ColumnInfo(name = "school_name") val schoolName: String?,
    @ColumnInfo(name = "neighborhood") val neighborhood: String?,
)