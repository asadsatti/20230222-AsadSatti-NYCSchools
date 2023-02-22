package com.example.nyc_school.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.nyc_school.PAGER_PAGE_SIZE
import com.example.nyc_school.domain.use_case.GetSchoolListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//https://developer.android.com/training/dependency-injection/hilt-jetpack
@HiltViewModel
class SchoolListViewModel @Inject constructor(
    getSchoolsUseCase: GetSchoolListUseCase): ViewModel() {
    val schools = getSchoolsUseCase(PAGER_PAGE_SIZE).cachedIn(viewModelScope)
}