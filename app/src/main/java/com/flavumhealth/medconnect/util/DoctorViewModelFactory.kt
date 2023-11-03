package com.flavumhealth.medconnect.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flavumhealth.medconnect.data.AppointmentRepository
import com.flavumhealth.medconnect.ui.doctor.DoctorViewModel

class DoctorViewModelFactory(private val repository: AppointmentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DoctorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DoctorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
