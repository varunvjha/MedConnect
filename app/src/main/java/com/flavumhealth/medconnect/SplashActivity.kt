package com.flavumhealth.medconnect

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        } else
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        runApp()
    }

    private fun runApp() {
            val background: Thread = object : Thread() {
                override fun run() {
                    try {
                        sleep(1000)
//                        val isLoggedIn =
//                            sharedPreferenceManager.get(IS_LOGGED_IN_V2, Constants.N) as String
//                        val hasGivenDataOnSignup =
//                            sharedPreferenceManager.get(USER_DATA_UPDATED, Constants.N) as String
//                        println("isLoggedIn : $isLoggedIn")
//                        println("hasGivenDataOnSignup : $hasGivenDataOnSignup")
//                        if (isLoggedIn.equals(Y, ignoreCase = true)) {
//                            HelperMethods.isSessionValid(this@SplashScreenActivity) { response ->
//                                val type: String =
//                                    if (response.has(TYPE)) response.getString(TYPE) else BLANK
//                                if (type.equals("danger", ignoreCase = true)) {
//                                    Toast.makeText(
//                                        this@SplashScreenActivity,
//                                        "Your session has expired. Please login again.",
//                                        Toast.LENGTH_LONG
//                                    ).show()
//                                    clearAnyData(true)
//                                } else {
//                                    if (hasGivenDataOnSignup.equals(Y, ignoreCase = true)) {
//                                        //Going to dashboard
//                                        val i =
//                                            Intent(baseContext, MainActivity::class.java)
//                                        i.flags =
//                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                        startActivity(i)
//                                    } else {
//                                        //Going to edit/add info screen
//                                        val i =
//                                            Intent(baseContext, UserInfoActivity::class.java)
//                                        i.flags =
//                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                        startActivity(i)
//                                    }
//                                }
//                            }
//                        } else {
//                            //Going to login screen
//                            clearAnyData(false)
//                        }
                        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            background.start()
//        } else {
//            Toast.makeText(this@SplashScreenActivity, "Internet not connected.", Toast.LENGTH_LONG)
//                .show()
//            retry.setVisibility(View.VISIBLE)
//            gif_relative_layout.setVisibility(View.GONE)
//        }
    }
}