package com.flavumhealth.medconnect.data

import com.flavumhealth.medconnect.data.model.Appointment
import com.flavumhealth.medconnect.data.model.AppointmentStatus

object MockDataGenerator {
    fun generateAppointments(): List<Appointment> {
        val appointments = mutableListOf<Appointment>()

        // Generate some appointments for Monday at 10 AM
        for (i in 1..5) {
            val dateTime = "Monday 10:00 AM"
            val appointment = Appointment(id = i.toString(), doctorId = "D00001", doctorName = "John Smith", dateTime = dateTime, status = AppointmentStatus.AVAILABLE)
            appointments.add(appointment)
        }

        // Generate some appointments for Friday at 10 AM
        for (i in 6..10) {
            val dateTime = "Friday 10:00 AM"
            val appointment = Appointment(id = i.toString(), doctorId = "D00001", doctorName = "John Smith", dateTime = dateTime, status = AppointmentStatus.AVAILABLE)
            appointments.add(appointment)
        }

        return appointments
    }
}