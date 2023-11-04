package com.flavumhealth.medconnect.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.flavumhealth.medconnect.R
import com.flavumhealth.medconnect.data.model.Appointment
import com.flavumhealth.medconnect.databinding.ItemAppointmentBinding
import com.flavumhealth.medconnect.ui.AppointmentSlotListener

class AppointmentAdapter(
    private val appointments: MutableList<Appointment> = mutableListOf(),
    private val slotListener: AppointmentSlotListener? = null
) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    private var lastClickedPosition: Int = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAppointmentBinding.inflate(inflater, parent, false)
        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val currentAppointment = appointments[position]
        holder.binding.appointment = currentAppointment
        holder.bind(currentAppointment)

        if (position == lastClickedPosition) {
            holder.llRoot.setBackgroundResource(R.drawable.rounded_card)
        } else {
            holder.llRoot.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        holder.itemView.setOnClickListener {
            notifyItemChanged(lastClickedPosition)
            holder.llRoot.setBackgroundResource(R.drawable.rounded_card)
            lastClickedPosition = holder.absoluteAdapterPosition

            slotListener?.onSlotClicked(currentAppointment)
        }
    }

    override fun getItemCount(): Int = appointments.size

    fun setAppointments(newAppointments: List<Appointment>) {
        appointments.clear()
        appointments.addAll(newAppointments)
        notifyDataSetChanged()
    }

    class AppointmentViewHolder(internal val binding: ItemAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val llRoot: LinearLayout = binding.llRoot
        fun bind(appointment: Appointment) {
            binding.tvDateTime.text = appointment.dateTime
        }
    }
}
