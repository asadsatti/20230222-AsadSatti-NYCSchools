package com.example.nyc_school.data.di

import com.example.nyc_school.data.api_service.NycSchoolNetworkDS
import com.example.nyc_school.data.api_service.RetrofitNycSchoolNetworkDS
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {
    @Binds
    fun RetrofitNycSchoolNetworkDS.binds(): NycSchoolNetworkDS
}
