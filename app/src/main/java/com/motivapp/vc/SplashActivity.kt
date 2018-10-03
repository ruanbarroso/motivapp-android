package com.motivapp.vc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.motivapp.service.PreferencesService

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region <! Verificando se o usuário já está logado !>
        if (PreferencesService.getUser(this) == null) {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        } else {
            startActivity(Intent(this@SplashActivity, TrainingsActivity::class.java))
        }
        //endregion

        finish()
    }

}