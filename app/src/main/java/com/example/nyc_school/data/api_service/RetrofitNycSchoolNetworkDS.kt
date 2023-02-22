package com.example.nyc_school.data.api_service

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNycSchoolNetworkDS @Inject constructor(
    private val networkApi: RetrofitNycSchoolNetworkApi,
) : NycSchoolNetworkDS {

    override suspend fun schoolList(limit: Int, offset: Int?) =
        networkApi.schoolList(limit = limit, offset =offset)

    override suspend fun schoolSatScore(dbn: String) =
        networkApi.schoolSatScore(dbn = dbn)
}
