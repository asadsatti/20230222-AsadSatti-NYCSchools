package com.example.nyc_school.data.di

import com.example.nyc_school.data.repo.DbNycSchoolRepoImpl
import com.example.nyc_school.data.repo.NycSchoolRepoImpl
import com.example.nyc_school.domain.repo.DbNycSchoolRepo
import com.example.nyc_school.domain.repo.NycSchoolRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindsDbNycSchoolRepo(
        dbNycSchoolRepoImpl: DbNycSchoolRepoImpl
    ): DbNycSchoolRepo

    @Binds
    abstract fun bindsNycSchoolRepo(
        nycSchoolRepoImpl: NycSchoolRepoImpl
    ): NycSchoolRepo
}