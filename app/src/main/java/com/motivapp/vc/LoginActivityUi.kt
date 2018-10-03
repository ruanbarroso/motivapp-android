package com.motivapp.vc

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.motivapp.R
import org.jetbrains.anko.*

class LoginActivityUi : AnkoComponent<LoginActivity> {

    lateinit var logoImageView: ImageView
    lateinit var emailTextView: TextView
    lateinit var emailEditText: EditText
    lateinit var passwordTextView: TextView
    lateinit var passwordEditText: EditText
    lateinit var loginButton: Button
    lateinit var registerTextView: TextView

    override fun createView(ui: AnkoContext<LoginActivity>) = with(ui) {
        relativeLayout {
            backgroundColor = Color.WHITE

            relativeLayout {

                logoImageView = imageView(R.mipmap.ic_launcher) {
                    id = View.generateViewId()
                }.lparams(dip(100), dip(100)) {
                    centerHorizontally()
                }

                emailTextView = textView(R.string.login_email) {
                    id = View.generateViewId()
                    textSize = 16f
                    textColor = ContextCompat.getColor(context, R.color.colorBlack)
                }.lparams {
                    marginStart = dip(20)
                    below(logoImageView)
                    topMargin = dip(30)
                }

                emailEditText = editText {
                    id = View.generateViewId()
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    textSize = 16f
                    textColor = ContextCompat.getColor(context, R.color.colorBlack)
                }.lparams(matchParent) {
                    marginStart = dip(20)
                    marginEnd = dip(20)
                    below(emailTextView)
                }

                passwordTextView = textView(R.string.login_password) {
                    id = View.generateViewId()
                    textSize = 16f
                    textColor = ContextCompat.getColor(context, R.color.colorBlack)
                }.lparams {
                    marginStart = dip(20)
                    below(emailEditText)
                    topMargin = dip(20)
                }

                passwordEditText = editText {
                    id = View.generateViewId()
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    textSize = 16f
                    textColor = ContextCompat.getColor(context, R.color.colorBlack)
                }.lparams(matchParent) {
                    marginStart = dip(20)
                    marginEnd = dip(20)
                    below(passwordTextView)
                }

                loginButton = button(R.string.login_login) {
                    id = View.generateViewId()
                    isAllCaps = false
                    padding = dip(20)
                    textSize = 16f
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textColor = Color.WHITE
                        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                    }
                }.lparams(matchParent) {
                    below(passwordEditText)
                    marginStart = dip(20)
                    marginEnd = dip(20)
                    topMargin = dip(20)
                }

                registerTextView = textView(R.string.login_register) {
                    id = View.generateViewId()
                    textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                    padding = dip(20)
                    textSize = 16f
                }.lparams {
                    below(loginButton)
                    centerHorizontally()
                }

            }.lparams(matchParent, wrapContent) {
                centerVertically()
            }

        }
    }

}