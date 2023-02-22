package com.example.nyc_school.data.repo

import com.example.nyc_school.data.di.IoDispatcher
import com.example.nyc_school.data.api_service.NycSchoolNetworkDS
import com.example.nyc_school.domain.repo.NycSchoolRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NycSchoolRepoImpl @Inject constructor(
    @IoDispatcher private val  ioDispatcher: CoroutineDispatcher,
    private val nycSchoolNetworkDS: NycSchoolNetworkDS
): NycSchoolRepo {
    // move the execution to an IO dispatcher to make it main-safe
    override suspend fun schoolSatScore(dbn: String) = withContext(ioDispatcher) {
        nycSchoolNetworkDS.schoolSatScore(dbn)
    }
}

