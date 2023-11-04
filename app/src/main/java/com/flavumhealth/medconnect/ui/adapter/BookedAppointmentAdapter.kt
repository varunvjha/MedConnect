package com.flavumhealth.medconnect.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flavumhealth.medconnect.data.model.Appointment
import com.flavumhealth.medconnect.databinding.ItemBookedAppointmentBinding

class BookedAppointmentAdapter :
    RecyclerView.Adapter<BookedAppointmentAdapter.BookedAppointmentViewHolder>() {

    private val bookedAppointments: MutableList<Appointment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookedAppointmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookedAppointmentBinding.inflate(inflater, parent, false)
        return BookedAppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookedAppointmentViewHolder, position: Int) {
        val currentAppointment = bookedAppointments[position]
        holder.binding.bookedAppointment = currentAppointment
        holder.bind(currentAppointment)
    }

    override fun getItemCount(): Int = bookedAppointments.size

    fun setBookedAppointments(newBookedAppointments: List<Appointment>) {
        bookedAppointments.clear()
        bookedAppointments.addAll(newBookedAppointments)
        notifyDataSetChanged()
    }

    class BookedAppointmentViewHolder(internal val binding: ItemBookedAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(appointment: Appointment) {
            binding.tvBookedDateTime.text = appointment.dateTime
        }
    }
}
