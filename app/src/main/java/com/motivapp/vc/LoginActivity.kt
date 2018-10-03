package com.motivapp.vc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.motivapp.R
import com.motivapp.model.User
import com.motivapp.service.PreferencesService
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView

class LoginActivity : AppCompatActivity() {

    companion object {
        const val REGISTER_REQUEST_CODE = 7586
    }

    private lateinit var ui: LoginActivityUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region <! Carregando o Layout !>
        ui = LoginActivityUi()
        ui.setContentView(this)
        //endregion

        //region <! Quando clicar em entrar !>
        ui.loginButton.onClick { _ ->
            if (ui.emailEditText.text.trim().isEmpty()) {
                alert(R.string.register_empty_email) {
                    okButton { }
                }.show()
                return@onClick
            }
            if (ui.passwordEditText.text.trim().isEmpty()) {
                alert(R.string.register_empty_password) {
                    okButton { }
                }.show()
                return@onClick
            }
            indeterminateProgressDialog(R.string.login_loading) {
                setCancelable(false)
                setOnShowListener { _ ->
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(ui.emailEditText.text.toString(), ui.passwordEditText.text.toString()).addOnCompleteListener { task ->
                        dismiss()
                        if (!task.isSuccessful) {
                            alert(task.exception?.message ?: getString(R.string.register_error)) {
                                okButton { }
                            }.show()
                            return@addOnCompleteListener
                        }
                        val user = User()
                        val firebaseUser = task.result.user
                        user.name = firebaseUser.displayName!!
                        user.email = firebaseUser.email!!
                        user.uid = firebaseUser.uid
                        PreferencesService.putUser(this@LoginActivity, user)
                        startActivity(Intent(this@LoginActivity, TrainingsActivity::class.java))
                        finish()
                    }
                }
            }.show()
        }
        //endregion

        //region <! Quando clicar em registrar !>
        ui.registerTextView.onClick {
            startActivityForResult(Intent(this@LoginActivity, RegisterActivity::class.java), REGISTER_REQUEST_CODE)
        }
        //endregion

    }

    //region <! Quando a atividade obter um resultado !>
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REGISTER_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                finish()
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    //endregion

}