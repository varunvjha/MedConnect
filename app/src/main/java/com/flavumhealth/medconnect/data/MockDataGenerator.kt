package com.flavumhealth.medconnect.data

import com.flavumhealth.medconnect.data.model.Appointment
import com.flavumhealth.medconnect.data.model.AppointmentStatus
import com.flavumhealth.medconnect.di.AppointmentDao

object MockDataGenerator {
    suspend fun insertAppointments(appointmentDao: AppointmentDao) {
        for (i in 1..5) {
            val dateTime = "Monday 10:00 AM"
            val appointment = Appointment(id = i.toString(), doctorId = "D00001", doctorName = "John Smith", dateTime = dateTime, status = AppointmentStatus.AVAILABLE)
            appointmentDao.insertAppointment(appointment)
        }

        for (i in 6..10) {
            val dateTime = "Friday 10:00 AM"
            val appointment = Appointment(id = i.toString(), doctorId = "D00001", doctorName = "John Smith", dateTime = dateTime, status = AppointmentStatus.AVAILABLE)
            appointmentDao.insertAppointment(appointment)
        }
    }
}