package com.flavumhealth.medconnect.ui.patient

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flavumhealth.medconnect.data.AppointmentRepository
import androidx.lifecycle.viewModelScope
import com.flavumhealth.medconnect.data.MockDataGenerator
import com.flavumhealth.medconnect.data.model.Appointment
import kotlinx.coroutines.launch

class PatientViewModel(private val repository: AppointmentRepository) : ViewModel() {

    private val _availableSlots = MutableLiveData<List<Appointment>>()
    val availableSlots: LiveData<List<Appointment>> get() = _availableSlots

    private val _bookedAppointments = MutableLiveData<List<Appointment>>()
    val bookedAppointments: LiveData<List<Appointment>> get() = _bookedAppointments

    private var selectedAppointment: Appointment? = null

    fun setSelectedAppointmentValue(appointment: Appointment) {
        selectedAppointment = appointment
    }

    fun bookAppointment(patientId: String) {
        viewModelScope.launch {
            selectedAppointment?.let {
                repository.bookAppointment(it)
                Log.d("PatientViewModel", "Appointment booked: $it")
                loadBookedAppointments(patientId)
            }
        }
    }

    private fun loadBookedAppointments(patientId: String) {
        viewModelScope.launch {
            repository.getBookedAppointments(patientId).observeForever { bookedAppointments ->
                _bookedAppointments.postValue(bookedAppointments)
            }
        }
    }

    init {
        _availableSlots.value = MockDataGenerator.generateAppointments()
        _bookedAppointments.value = emptyList()
    }
}