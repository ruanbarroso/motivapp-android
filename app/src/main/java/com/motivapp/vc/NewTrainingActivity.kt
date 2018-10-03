package com.motivapp.vc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.motivapp.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView

class NewTrainingActivity : AppCompatActivity() {

    private lateinit var ui: NewTrainingActivityUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region <! Carregando o Layout !>
        ui = NewTrainingActivityUi()
        ui.setContentView(this)
        //endregion

        //region <! Carregando os títulos !>
        indeterminateProgressDialog(R.string.new_training_loading) {
            setCancelable(false)
            setOnShowListener { _ ->
                FirebaseFirestore.getInstance().collection("training-titles").get().addOnCompleteListener { task ->
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
                        alert(R.string.new_training_no_titles) {
                            isCancelable = false
                            okButton {
                                finish()
                            }
                        }.show()
                        return@addOnCompleteListener
                    }
                    val titles = arrayListOf<String?>()
                    for (document in task.result.documents) {
                        titles.add(document.data?.get("title") as? String)
                    }
                    val adapter = ArrayAdapter<String>(this@NewTrainingActivity, android.R.layout.simple_spinner_item, titles)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    ui.titleSpinner.adapter = adapter
                }
            }
        }.show()
        //endregion

        //region <! Quando clicar na imagem !>
        ui.imageImageView.onClick {

        }
        //endregion

        //region <! Quando clicar em salvar !>
        ui.saveButton.onClick {

        }
        //endregion

    }

}