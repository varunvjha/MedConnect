package com.flavumhealth.medconnect.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey val id: String,
    val doctorId: String,
    val doctorName: String,
    var patientId: String? = null,
    val dateTime: String,
    var status: AppointmentStatus
)

enum class AppointmentStatus {
    AVAILABLE,
    BOOKED,
    PENDING
}
