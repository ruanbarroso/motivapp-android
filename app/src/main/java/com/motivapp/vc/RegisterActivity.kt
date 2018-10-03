package com.motivapp.vc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.motivapp.R
import com.motivapp.model.User
import com.motivapp.service.PreferencesService
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView

class RegisterActivity : AppCompatActivity() {

    private lateinit var ui: RegisterActivityUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region <! Carregando o Layout !>
        ui = RegisterActivityUi()
        ui.setContentView(this)
        //endregion

        //region <! Quando clicar em registrar !>
        ui.registerButton.onClick { _ ->
            if (ui.nameEditText.text.trim().isEmpty()) {
                alert(R.string.register_empty_name) {
                    okButton { }
                }.show()
                return@onClick
            }
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
            indeterminateProgressDialog(R.string.register_loading) {
                setCancelable(false)
                setOnShowListener { _ ->
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(ui.emailEditText.text.toString(), ui.passwordEditText.text.toString()).addOnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            dismiss()
                            alert(task.exception?.message ?: getString(R.string.register_error)) {
                                okButton { }
                            }.show()
                            return@addOnCompleteListener
                        }
                        val user = User()
                        val firebaseUser = task.result.user
                        user.name = ui.nameEditText.text.toString()
                        user.email = firebaseUser.email!!
                        user.uid = firebaseUser.uid
                        val userChangeRequest = UserProfileChangeRequest.Builder().setDisplayName(user.name).build()
                        firebaseUser.updateProfile(userChangeRequest).addOnCompleteListener { task2 ->
                            dismiss()
                            if (!task2.isSuccessful) {
                                alert(task.exception?.message
                                        ?: getString(R.string.register_error)) {
                                    okButton { }
                                }.show()
                            } else {
                                PreferencesService.putUser(this@RegisterActivity, user)
                                startActivity(Intent(this@RegisterActivity, TrainingsActivity::class.java))
                                setResult(Activity.RESULT_OK)
                                finish()
                            }

                        }
                    }
                }
            }.show()
        }
        //endregion

    }

}