package com.flavumhealth.medconnect.data

import androidx.lifecycle.LiveData
import com.flavumhealth.medconnect.data.model.Appointment
import com.flavumhealth.medconnect.data.model.AppointmentStatus
import com.flavumhealth.medconnect.di.AppointmentDao

class AppointmentRepository(private val appointmentDao: AppointmentDao) {

    companion object {
        @Volatile
        private var instance: AppointmentRepository? = null

        fun getInstance(appointmentDao: AppointmentDao): AppointmentRepository {
            return instance ?: synchronized(this) {
                instance ?: AppointmentRepository(appointmentDao).also { instance = it }
            }
        }
    }

    val availableSlots: LiveData<List<Appointment>> = appointmentDao.getAvailableSlots()

    suspend fun bookAppointment(appointment: Appointment) {
        appointment.status = AppointmentStatus.BOOKED
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
