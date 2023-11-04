package com.flavumhealth.medconnect.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flavumhealth.medconnect.data.model.Appointment

@Database(entities = [Appointment::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appointmentDao(): AppointmentDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "medconnect_database"
            ).fallbackToDestructiveMigration().build()
        }
    }
}
