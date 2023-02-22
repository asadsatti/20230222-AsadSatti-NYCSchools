package com.example.nyc_school.data.api_service

import com.example.nyc_school.data.dto.SatScore
import com.example.nyc_school.data.db.School

interface NycSchoolNetworkDS {
    suspend fun schoolList(limit: Int, offset: Int?) : Result<List<School>>
    suspend fun schoolSatScore(dbn: String) : Result<List<SatScore>>
}