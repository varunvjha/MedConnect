package com.flavumhealth.medconnect.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey val id: String,
    val doctorId: String,
    val patientId: String,
    val dateTime: String,
    val status: AppointmentStatus
)

enum class AppointmentStatus {
    AVAILABLE,
    BOOKED,
    PENDING
}
