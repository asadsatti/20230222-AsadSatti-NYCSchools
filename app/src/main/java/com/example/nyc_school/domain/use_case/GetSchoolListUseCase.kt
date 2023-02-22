package com.example.nyc_school.domain.use_case

import androidx.paging.PagingData
import com.example.nyc_school.data.db.School
import com.example.nyc_school.domain.repo.DbNycSchoolRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSchoolListUseCase @Inject constructor(private val nycSchoolRepo: DbNycSchoolRepo) {
    operator fun invoke(pageSize: Int): Flow<PagingData<School>> {
        return nycSchoolRepo.schoolList(pageSize)
    }
}