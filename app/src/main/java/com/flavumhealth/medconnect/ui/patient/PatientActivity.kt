package com.flavumhealth.medconnect.ui.patient

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flavumhealth.medconnect.R
import com.flavumhealth.medconnect.data.AppointmentRepository
import com.flavumhealth.medconnect.ui.adapter.AppointmentAdapter
import com.flavumhealth.medconnect.util.PatientViewModelFactory

class PatientActivity : AppCompatActivity() {
    private lateinit var viewModel: PatientViewModel
    private lateinit var repository: AppointmentRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        repository = intent.getSerializableExtra("repository") as AppointmentRepository

        viewModel = ViewModelProvider(this, PatientViewModelFactory(repository)).get(PatientViewModel::class.java)


        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            finish()
        }

        val availableSlotsAdapter = AppointmentAdapter()
        findViewById<RecyclerView>(R.id.rvAvailableSlots).apply {
            layoutManager = LinearLayoutManager(this@PatientActivity)
            adapter = availableSlotsAdapter
        }

        viewModel.availableSlots.observe(this) { availableSlots ->
            availableSlotsAdapter.setAppointments(availableSlots)
        }

        val bookedAppointmentsAdapter = AppointmentAdapter()
        findViewById<RecyclerView>(R.id.rvBookedAppointments).apply {
            layoutManager = LinearLayoutManager(this@PatientActivity)
            adapter = bookedAppointmentsAdapter
        }

        findViewById<Button>(R.id.btnBookAppointment).setOnClickListener {
            val selectedSlot = viewModel.availableSlots.value?.get(0)

            if (selectedSlot != null) {
                viewModel.bookAppointment(selectedSlot)
            }
        }

        viewModel.availableSlots.observe(this, Observer { availableSlots ->
            availableSlotsAdapter.setAppointments(availableSlots)
        })
    }
}
