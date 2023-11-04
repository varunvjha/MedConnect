package com.flavumhealth.medconnect.ui.patient

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flavumhealth.medconnect.R
import com.flavumhealth.medconnect.data.AppointmentRepository
import com.flavumhealth.medconnect.data.RepositoryHolder
import com.flavumhealth.medconnect.data.model.Appointment
import com.flavumhealth.medconnect.ui.AppointmentSlotListener
import com.flavumhealth.medconnect.ui.adapter.AppointmentAdapter
import com.flavumhealth.medconnect.ui.adapter.BookedAppointmentAdapter
import com.flavumhealth.medconnect.util.PatientViewModelFactory

class PatientActivity : AppCompatActivity() {
    private lateinit var viewModel: PatientViewModel
    private lateinit var repository: AppointmentRepository
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var bookedAdapter: BookedAppointmentAdapter
    private lateinit var patientId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        patientId = intent.getStringExtra("user_id")!!

        repository = RepositoryHolder.appointmentRepository
            ?: throw IllegalStateException("Repository must be set before starting PatientActivity")

        viewModel = ViewModelProvider(
            this, PatientViewModelFactory(repository)
        )[PatientViewModel::class.java]

        findViewById<TextView>(R.id.btnLogout).setOnClickListener {
            finish()
        }

        appointmentAdapter = AppointmentAdapter (mutableListOf(), object: AppointmentSlotListener {
            override fun onSlotClicked(appointment: Appointment) {
                slotClicked(appointment)
            }
        })
        findViewById<RecyclerView>(R.id.rvAvailableSlots).apply {
            layoutManager = LinearLayoutManager(this@PatientActivity)
            adapter = appointmentAdapter
        }

        viewModel.availableSlots.observe(this) { availableSlots ->
            appointmentAdapter.setAppointments(availableSlots)
        }

        bookedAdapter = BookedAppointmentAdapter()
        findViewById<RecyclerView>(R.id.rvBookedAppointments).apply {
            layoutManager = LinearLayoutManager(this@PatientActivity)
            adapter = bookedAdapter
        }

        findViewById<Button>(R.id.btnBookAppointment).setOnClickListener {
            viewModel.bookAppointment()
        }

        viewModel.bookedAppointments.observe(this) { bookedAppointments ->
            bookedAdapter.setBookedAppointments(bookedAppointments)
        }
    }

    private fun slotClicked(selectedSlot: Appointment) {
        selectedSlot.patientId = patientId
        viewModel.setSelectedAppointmentValue(selectedSlot)
    }
}
