package com.flavumhealth.medconnect.ui.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flavumhealth.medconnect.LoginActivity
import com.flavumhealth.medconnect.R
import com.flavumhealth.medconnect.data.AppointmentRepository
import com.flavumhealth.medconnect.data.RepositoryHolder
import com.flavumhealth.medconnect.ui.adapter.DoctorAppointmentAdapter
import com.flavumhealth.medconnect.util.DoctorViewModelFactory

class DoctorActivity : AppCompatActivity() {
    private lateinit var viewModel: DoctorViewModel
    private lateinit var repository: AppointmentRepository
    private lateinit var doctorAppointmentAdapter: DoctorAppointmentAdapter
    private lateinit var doctorId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        doctorId = intent.getStringExtra("user_id")!!

        repository = RepositoryHolder.appointmentRepository
            ?: throw IllegalStateException("Repository must be set before starting DoctorActivity")

        viewModel = ViewModelProvider(
            this, DoctorViewModelFactory(repository)
        )[DoctorViewModel::class.java]

        doctorAppointmentAdapter = DoctorAppointmentAdapter()

        findViewById<RecyclerView>(R.id.rvDoctorAppointments).apply {
            layoutManager = LinearLayoutManager(this@DoctorActivity)
            adapter = doctorAppointmentAdapter
        }

        viewModel.getDoctorAppointments(doctorId).observe(this) { doctorAppointments ->
            doctorAppointmentAdapter.setAppointments(doctorAppointments)
        }

        findViewById<TextView>(R.id.btnLogout).setOnClickListener {
            val intent = Intent(this@DoctorActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
