package com.example.nyc_school.data.repo

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.nyc_school.data.db.SchoolDB
import com.example.nyc_school.data.api_service.NycSchoolNetworkDS
import com.example.nyc_school.data.db.School
import com.example.nyc_school.data.db.ApiRemoteKey

@OptIn(ExperimentalPagingApi::class)
class PageKeyedRemoteMediator(
    private val schoolDB: SchoolDB,
    private val nycSchoolService: NycSchoolNetworkDS
): RemoteMediator<Int, School>() {

    override suspend fun initialize(): InitializeAction {
        // Remote refresh is launched on initial load and
        // succeeds before launching remote PREPEND / APPEND
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, School>): MediatorResult {
        val schoolDao = schoolDB.schoolDao()
        val apiRemoteKeyDao = schoolDB.apiRemoteKeyDao()
        // closest item from PagingState that we want to load data around
        val loadKey = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                // get remoteKey for NYC school
                // It keep track of page keys to fetch the next
                val remoteKey = schoolDB.withTransaction {
                    apiRemoteKeyDao.remoteKeyByApiName(API_NAME_NYC_SCHOOL)
                }

                remoteKey.apiNextPage
            }
        }
        Log.d("TAG", ">>> loadType: $loadType initialLoadSize: ${state.config.initialLoadSize} pageSize: ${state.config.pageSize}")
        val result = nycSchoolService.schoolList(
            limit = when (loadType) {
                LoadType.REFRESH -> state.config.initialLoadSize
                else -> state.config.pageSize
            },
            offset = loadKey
        )

        result.onSuccess { schools ->
            schoolDB.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    schoolDao.deleteAllSchools()
                    apiRemoteKeyDao.deleteByApiName(API_NAME_NYC_SCHOOL)
                }

                apiRemoteKeyDao.insert(
                    ApiRemoteKey(API_NAME_NYC_SCHOOL,
                    schoolDao.getRowCount() + schools.size)
                )
                schoolDao.insertAll(schools)
            }
            return MediatorResult.Success(endOfPaginationReached = schools.isEmpty())
        }.onFailure {
            return MediatorResult.Error(it)
        }

        return MediatorResult.Error(IllegalAccessError("Invalid State"))
    }

    companion object {
        private const val API_NAME_NYC_SCHOOL = "nyc_school"
    }
}