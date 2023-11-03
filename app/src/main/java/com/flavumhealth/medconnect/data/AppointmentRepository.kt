package com.flavumhealth.medconnect.data

import androidx.lifecycle.LiveData
import com.flavumhealth.medconnect.data.model.Appointment
import com.flavumhealth.medconnect.di.AppointmentDao

class AppointmentRepository(private val appointmentDao: AppointmentDao) {
    val availableSlots: LiveData<List<Appointment>> = appointmentDao.getAvailableSlots()

    suspend fun bookAppointment(appointment: Appointment) {
        appointmentDao.bookAppointment(appointment)
    }

    fun getBookedAppointments(patientId: String): LiveData<List<Appointment>> {
        return appointmentDao.getBookedAppointments(patientId)
    }

    fun getPendingAppointments(doctorId: String): LiveData<List<Appointment>> {
        return appointmentDao.getPendingAppointments(doctorId)
    }

    fun getAllAppointments(doctorId: String): LiveData<List<Appointment>> {
        return appointmentDao.getAllAppointments(doctorId)
    }
}
