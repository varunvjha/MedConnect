package com.flavumhealth.medconnect.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flavumhealth.medconnect.data.model.Appointment
import com.flavumhealth.medconnect.databinding.ItemDoctorAppointmentBinding

class DoctorAppointmentAdapter(private val appointments: MutableList<Appointment> = mutableListOf()) :
    RecyclerView.Adapter<DoctorAppointmentAdapter.DoctorAppointmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorAppointmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDoctorAppointmentBinding.inflate(inflater, parent, false)
        return DoctorAppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorAppointmentViewHolder, position: Int) {
        val currentAppointment = appointments[position]
        println("CurrentAppointment ${currentAppointment.dateTime}")
        holder.binding.appointment = currentAppointment
        holder.bind(currentAppointment)
    }

    override fun getItemCount(): Int = appointments.size

    fun setAppointments(newAppointments: List<Appointment>) {
        appointments.clear()
        appointments.addAll(newAppointments)
        notifyDataSetChanged()
    }

    class DoctorAppointmentViewHolder(internal val binding: ItemDoctorAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(appointment: Appointment) {
            binding.tvPatientName.text = appointment.patientId // Replace with the actual patient details
            binding.tvDateTime.text = appointment.dateTime
        }
    }
}
