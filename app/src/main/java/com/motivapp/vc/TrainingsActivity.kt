package com.motivapp.vc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.motivapp.R
import com.motivapp.adapter.TrainingAdapter
import com.motivapp.model.Training
import com.motivapp.service.PreferencesService
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView

class TrainingsActivity : AppCompatActivity() {

    private lateinit var ui: TrainingsActivityUi
    private val trainings = arrayListOf<Training>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region <! Carregando o Layout !>
        ui = TrainingsActivityUi()
        ui.setContentView(this)
        //endregion

        //region <! Carregando a lista de treinos !>
        ui.trainingsListView.adapter = TrainingAdapter(this, trainings)
        indeterminateProgressDialog(R.string.trainings_loading) {
            setCancelable(false)
            setOnShowListener { _ ->
                FirebaseFirestore.getInstance().collection("trainings").get().addOnCompleteListener { task ->
                    dismiss()
                    if (!task.isSuccessful) {
                        alert(task.exception?.message ?: getString(R.string.register_error)) {
                            isCancelable = false
                            okButton {
                                finish()
                            }
                        }.show()
                        return@addOnCompleteListener
                    }
                    if (task.result.isEmpty) {
                        return@addOnCompleteListener
                    }
                }
            }
        }.show()
        //endregion

        //region <! Quando clicar em buscar !>
        ui.searchCardView.onClick {
            startActivity(Intent(this@TrainingsActivity, SearchActivity::class.java))
        }
        //endregion

        //region <! Quando clicar no botão de adicionar !>
        ui.addFloatingActionButton.onClick {
            startActivity(Intent(this@TrainingsActivity, NewTrainingActivity::class.java))
        }
        //endregion

    }

    //region <! Quando voltar !>
    override fun onBackPressed() {
        PreferencesService.removeUser(this)
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
    //endregion

}