package com.example.nyc_school.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.nyc_school.data.db.SchoolDB
import com.example.nyc_school.data.api_service.NycSchoolNetworkDS
import com.example.nyc_school.domain.repo.DbNycSchoolRepo
import javax.inject.Inject

class DbNycSchoolRepoImpl @Inject constructor(
    private val schoolDB: SchoolDB,
    private val nycSchoolNetworkDS: NycSchoolNetworkDS,
): DbNycSchoolRepo  {

    @OptIn(ExperimentalPagingApi::class)
    override fun schoolList(pageSize: Int) = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = PageKeyedRemoteMediator(schoolDB, nycSchoolNetworkDS)
    ) {
        schoolDB.schoolDao().getAllSchools()
    }.flow
}

