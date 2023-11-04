package com.flavumhealth.medconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.flavumhealth.medconnect.data.AppointmentRepository
import com.flavumhealth.medconnect.data.RepositoryHolder
import com.flavumhealth.medconnect.di.AppDatabase
import com.flavumhealth.medconnect.ui.patient.PatientActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private lateinit var etUserId: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var repository: AppointmentRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUserId = findViewById(R.id.user_id_edit_text)
        etPassword = findViewById(R.id.password_edit_text)

        val appointmentDao = AppDatabase.getInstance(applicationContext).appointmentDao()
        repository = AppointmentRepository.getInstance(appointmentDao)

        findViewById<Button>(R.id.login_button).setOnClickListener {
            val userId = etUserId.text.toString()
            val password = etPassword.text.toString()

            if (isValidCredentials(userId, password)) {

                RepositoryHolder.appointmentRepository = repository

                lateinit var intent: Intent
                if (userId.startsWith("P", ignoreCase = true)) {
                    intent = Intent(this, PatientActivity::class.java)
                } else if (userId.startsWith("D", ignoreCase = true)) {
                    //TODO doctor activity
                }
                intent.putExtra("user_id", userId)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidCredentials(userId: String, password: String): Boolean {
        return (userId == "P00001" || userId == "D00001") && password == "password"
    }
}
