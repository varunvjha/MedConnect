package com.flavumhealth.medconnect.data

import com.flavumhealth.medconnect.data.model.Appointment
import com.flavumhealth.medconnect.data.model.AppointmentStatus
import com.flavumhealth.medconnect.di.AppointmentDao
import java.text.SimpleDateFormat
import java.util.*

object MockDataGenerator {
    suspend fun insertAppointments(appointmentDao: AppointmentDao) {
        val currentDate = Calendar.getInstance()
        for (week in 0 until 5) {
            for (dayOfWeek in arrayOf(Calendar.MONDAY, Calendar.FRIDAY)) {
                currentDate.set(Calendar.DAY_OF_WEEK, dayOfWeek)
                currentDate.set(Calendar.HOUR_OF_DAY, 10)
                currentDate.set(Calendar.MINUTE, 0)

                val dateFormat = SimpleDateFormat("EEE MMM dd yyyy, hh:mm a", Locale.US)
                val formattedDate = dateFormat.format(currentDate.time)

                val appointment = Appointment(
                    id = "$week$dayOfWeek",
                    doctorId = "D00001",
                    doctorName = "John Smith",
                    dateTime = formattedDate,
                    status = AppointmentStatus.AVAILABLE
                )

                appointmentDao.insertAppointment(appointment)
            }
            currentDate.add(Calendar.WEEK_OF_YEAR, 1)
        }
    }
}
