package com.flavumhealth.medconnect.ui.doctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.flavumhealth.medconnect.data.AppointmentRepository
import com.flavumhealth.medconnect.data.model.Appointment

class DoctorViewModel(private val repository: AppointmentRepository) : ViewModel() {
    fun getPendingAppointments(doctorId: String): LiveData<List<Appointment>> {
        return repository.getPendingAppointments(doctorId)
    }

    fun getAllAppointments(doctorId: String): LiveData<List<Appointment>> {
        return repository.getAllAppointments(doctorId)
    }
}
