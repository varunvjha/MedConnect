package com.flavumhealth.medconnect.di

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flavumhealth.medconnect.data.model.Appointment

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM appointments WHERE status = 'AVAILABLE'")
    fun getAvailableSlots(): LiveData<List<Appointment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookAppointment(appointment: Appointment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: Appointment)

    @Query("SELECT * FROM appointments WHERE patientId = :patientId")
    fun getBookedAppointments(patientId: String): LiveData<List<Appointment>>

    @Query("SELECT * FROM appointments WHERE doctorId = :doctorId AND status = 'PENDING'")
    fun getPendingAppointments(doctorId: String): LiveData<List<Appointment>>

    @Query("SELECT * FROM appointments WHERE doctorId = :doctorId AND status = 'BOOKED'")
    fun getAllBookedAppointments(doctorId: String): LiveData<List<Appointment>>
}
