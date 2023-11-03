package com.flavumhealth.medconnect.ui.patient

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.flavumhealth.medconnect.data.AppointmentRepository
import androidx.lifecycle.viewModelScope
import com.flavumhealth.medconnect.data.model.Appointment
import kotlinx.coroutines.launch

class PatientViewModel(private val repository: AppointmentRepository) : ViewModel() {
    val availableSlots: LiveData<List<Appointment>> = repository.availableSlots

    fun bookAppointment(appointment: Appointment) {
        viewModelScope.launch {
            repository.bookAppointment(appointment)
        }
    }

    fun getBookedAppointments(patientId: String): LiveData<List<Appointment>> {
        return repository.getBookedAppointments(patientId)
    }
}