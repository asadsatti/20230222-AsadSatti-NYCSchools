package com.example.nyc_school.domain.repo

import androidx.paging.PagingData
import com.example.nyc_school.data.db.School
import kotlinx.coroutines.flow.Flow

interface DbNycSchoolRepo {
    fun schoolList(pageSize: Int): Flow<PagingData<School>>
}