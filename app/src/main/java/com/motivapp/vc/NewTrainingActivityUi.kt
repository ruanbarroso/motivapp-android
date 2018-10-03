package com.motivapp.vc

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.*
import com.motivapp.R
import com.motivapp.component.ActionRelativeLayout
import com.motivapp.component.actionRelativeLayout
import org.jetbrains.anko.*

class NewTrainingActivityUi : AnkoComponent<NewTrainingActivity> {

    lateinit var actionRelativeLayout: ActionRelativeLayout
    lateinit var newTrainingTextView: TextView
    lateinit var titleTextView: TextView
    lateinit var titleSpinner: Spinner
    lateinit var descriptionTextView: TextView
    lateinit var descriptionEditText: EditText
    lateinit var imageTextView: TextView
    lateinit var imageImageView: ImageView
    lateinit var saveButton: Button

    override fun createView(ui: AnkoContext<NewTrainingActivity>) = with(ui) {
        relativeLayout {
            backgroundColor = Color.WHITE

            actionRelativeLayout = actionRelativeLayout {
                ankoContext = ui
            }

            newTrainingTextView = textView(R.string.new_training_training) {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 32f
                setTypeface(typeface, Typeface.BOLD)
            }.lparams {
                below(actionRelativeLayout)
                topMargin = dip(10)
                marginStart = dip(10)
            }

            titleTextView = textView(R.string.new_training_title) {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 16f
            }.lparams {
                below(newTrainingTextView)
                topMargin = dip(20)
                marginStart = dip(10)
            }

            titleSpinner = spinner {
                id = View.generateViewId()
            }.lparams(matchParent) {
                below(titleTextView)
                topMargin = dip(10)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

            descriptionTextView = textView(R.string.new_training_description) {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 16f
            }.lparams {
                below(titleSpinner)
                topMargin = dip(20)
                marginStart = dip(10)
            }

            descriptionEditText = editText {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 16f
                maxLines = 2
                gravity = Gravity.START
            }.lparams(matchParent) {
                below(descriptionTextView)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

            imageTextView = textView(R.string.new_training_image) {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 16f
            }.lparams {
                below(descriptionEditText)
                topMargin = dip(20)
                marginStart = dip(10)
            }

            saveButton = button(R.string.new_training_save) {
                id = View.generateViewId()
                textSize = 16f
                padding = dip(20)
                isAllCaps = false
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textColor = Color.WHITE
                    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                }
            }.lparams(matchParent) {
                alignParentBottom()
                marginStart = dip(10)
                marginEnd = dip(10)
                bottomMargin = dip(10)
            }

            imageImageView = imageView {
                id = View.generateViewId()
                backgroundColor = ContextCompat.getColor(context, R.color.colorGrayLight)
            }.lparams(matchParent, matchParent) {
                below(imageTextView)
                topMargin = dip(5)
                above(saveButton)
                bottomMargin = dip(20)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

        }
    }

}