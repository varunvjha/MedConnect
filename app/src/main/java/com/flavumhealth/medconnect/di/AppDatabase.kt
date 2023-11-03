package com.flavumhealth.medconnect.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flavumhealth.medconnect.data.model.Appointment

@Database(entities = [Appointment::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appointmentDao(): AppointmentDao
}
