package com.example.nyc_school.data.api_service

import com.example.nyc_school.data.dto.SatScore
import com.example.nyc_school.data.db.School
import retrofit2.http.GET
import retrofit2.http.Query

interface  RetrofitNycSchoolNetworkApi {

    // Example https://data.cityofnewyork.us/resource/s3k6-pzi2.json?$limit=2&$offset=0
    @GET("/resource/s3k6-pzi2.json")
    suspend fun schoolList(@Query("\$limit") limit: Int,
                           @Query("\$offset") offset: Int?) : Result<List<School>>

    @GET("/resource/f9bf-2cp4.json")
    suspend fun schoolSatScore(@Query("dbn") dbn: String) : Result<List<SatScore>>
}