package com.flavumhealth.medconnect.ui

import com.flavumhealth.medconnect.data.model.Appointment

interface AppointmentSlotListener {
    fun onSlotClicked(appointment: Appointment)
}