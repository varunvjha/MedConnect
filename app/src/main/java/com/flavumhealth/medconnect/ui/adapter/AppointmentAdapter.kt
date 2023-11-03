package com.flavumhealth.medconnect.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flavumhealth.medconnect.data.model.Appointment
import com.flavumhealth.medconnect.databinding.ItemAppointmentBinding

class AppointmentAdapter(private val appointments: MutableList<Appointment> = mutableListOf()) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAppointmentBinding.inflate(inflater, parent, false)
        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val currentAppointment = appointments[position]
        holder.bind(currentAppointment)
    }

    override fun getItemCount(): Int = appointments.size

    fun setAppointments(newAppointments: List<Appointment>) {
        appointments.clear()
        appointments.addAll(newAppointments)
        notifyDataSetChanged()
    }

    class AppointmentViewHolder(private val binding: ItemAppointmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(appointment: Appointment) {
            binding.tvDateTime.text = appointment.dateTime
        }
    }
}
