package com.motivapp.component

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewManager
import android.widget.ImageView
import android.widget.TextView
import com.motivapp.R
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.sdk25.coroutines.onClick

fun ViewManager.actionRelativeLayout(): ActionRelativeLayout = actionRelativeLayout {}
inline fun ViewManager.actionRelativeLayout(init: (@AnkoViewDslMarker ActionRelativeLayout).() -> Unit): ActionRelativeLayout =
        ankoView({ ctx: Context -> ActionRelativeLayout(ctx) }, theme = 0) { init() }

class ActionRelativeLayout(ctx: Context) : _RelativeLayout(ctx) {

    var ankoContext: AnkoContext<Any>? = null
        set(value) {
            field = value
            backImageView.layoutParams.width = if (field == null) 0 else dip(44)
            backImageView.onClick {
                ((ankoContext?.owner as? Fragment)?.activity
                        ?: ankoContext?.owner as? AppCompatActivity)?.onBackPressed()
            }
        }

    var title = ""
        set(value) {
            field = value
            titleTextView.text = field
        }

    private val backImageView: ImageView
    private val titleTextView: TextView

    init {
        id = View.generateViewId()

        backImageView = imageView {
            id = View.generateViewId()
            imageResource = R.drawable.ic_back_white
            setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary))
            leftPadding = dip(10)
            rightPadding = dip(10)
            onClick {

            }
        }.lparams(0, matchParent) {
            alignParentStart()
        }

        titleTextView = textView {
            id = View.generateViewId()
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
            maxLines = 1
            textSize = 16f
            ellipsize = TextUtils.TruncateAt.END
        }.lparams(matchParent, matchParent) {
            marginStart = dip(50)
            marginEnd = dip(50)
        }

        lparams(matchParent, dip(50))
    }

}