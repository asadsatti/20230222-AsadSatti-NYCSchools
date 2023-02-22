package com.example.nyc_school.domain.use_case

import com.example.nyc_school.data.dto.SatScore
import com.example.nyc_school.domain.repo.NycSchoolRepo
import javax.inject.Inject

class GetSchoolSatUseCase @Inject constructor(private val nycSchoolRepo: NycSchoolRepo) {
    suspend operator fun invoke(dbn: String): SatScore? {
        nycSchoolRepo.schoolSatScore(dbn).onSuccess {
            return if (it.isNotEmpty()) {
                it[0]
            } else {
                null
            }
        }.onFailure {
            return null
        }
        return null
    }
}