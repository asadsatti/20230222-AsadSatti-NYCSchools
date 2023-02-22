package com.example.nyc_school.domain.repo

import com.example.nyc_school.data.dto.SatScore

interface NycSchoolRepo {
    suspend fun schoolSatScore(dbn: String): Result<List<SatScore>>
}