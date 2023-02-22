package com.example.nyc_school.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyc_school.data.dto.SatScore
import com.example.nyc_school.domain.use_case.GetSchoolSatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolSatViewModel @Inject constructor(
    private val schoolSatUseCase: GetSchoolSatUseCase): ViewModel() {

    val satScore: LiveData<SatScore?>
    get() = _satScore
    private val _satScore = MutableLiveData<SatScore?>()

    fun getSatScore(dbn: String) {
        viewModelScope.launch {
            val satScore = schoolSatUseCase(dbn)
            _satScore.value = satScore
        }
    }
}