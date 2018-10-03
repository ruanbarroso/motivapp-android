package com.motivapp.vc

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.motivapp.R
import com.motivapp.component.ActionRelativeLayout
import com.motivapp.component.actionRelativeLayout
import org.jetbrains.anko.*

class RegisterActivityUi : AnkoComponent<RegisterActivity> {

    lateinit var actionRelativeLayout: ActionRelativeLayout
    lateinit var registerTextView: TextView
    lateinit var nameTextView: TextView
    lateinit var nameEditText: EditText
    lateinit var emailTextView: TextView
    lateinit var emailEditText: EditText
    lateinit var passwordTextView: TextView
    lateinit var passwordEditText: EditText
    lateinit var registerButton: Button

    override fun createView(ui: AnkoContext<RegisterActivity>) = with(ui) {
        relativeLayout {
            backgroundColor = Color.WHITE

            actionRelativeLayout = actionRelativeLayout {
                ankoContext = ui
            }

            registerTextView = textView(R.string.register_message) {
                id = View.generateViewId()
                textSize = 24f
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                setTypeface(typeface, Typeface.BOLD)
            }.lparams {
                below(actionRelativeLayout)
                topMargin = dip(10)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

            nameTextView = textView(R.string.register_name) {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 16f
            }.lparams {
                below(registerTextView)
                topMargin = dip(20)
                marginStart = dip(10)
            }

            nameEditText = editText {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 16f
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            }.lparams(matchParent) {
                below(nameTextView)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

            emailTextView = textView(R.string.register_email) {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 16f
            }.lparams {
                below(nameEditText)
                topMargin = dip(20)
                marginStart = dip(10)
            }

            emailEditText = editText {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 16f
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }.lparams(matchParent) {
                below(emailTextView)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

            passwordTextView = textView(R.string.register_password) {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 16f
            }.lparams {
                below(emailEditText)
                topMargin = dip(20)
                marginStart = dip(10)
            }

            passwordEditText = editText {
                id = View.generateViewId()
                textColor = ContextCompat.getColor(context, R.color.colorBlack)
                textSize = 16f
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }.lparams(matchParent) {
                below(passwordTextView)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

            registerButton = button(R.string.register_register) {
                id = View.generateViewId()
                isAllCaps = false
                padding = dip(20)
                textSize = 16f
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

        }
    }

}