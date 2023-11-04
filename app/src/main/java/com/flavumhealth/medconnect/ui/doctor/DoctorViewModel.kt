package com.flavumhealth.medconnect.ui.doctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flavumhealth.medconnect.data.AppointmentRepository
import com.flavumhealth.medconnect.data.model.Appointment

class DoctorViewModel(private val repository: AppointmentRepository) : ViewModel() {
    private val _doctorAppointments = MutableLiveData<List<Appointment>>()
    val doctorAppointments: LiveData<List<Appointment>> get() = _doctorAppointments

    fun getDoctorAppointments(doctorId: String): LiveData<List<Appointment>> {
        return repository.getAllAppointments(doctorId)
    }
}
