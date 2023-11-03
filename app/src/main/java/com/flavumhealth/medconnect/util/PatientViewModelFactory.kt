package com.flavumhealth.medconnect.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flavumhealth.medconnect.data.AppointmentRepository
import com.flavumhealth.medconnect.ui.patient.PatientViewModel

class PatientViewModelFactory(private val repository: AppointmentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatientViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}