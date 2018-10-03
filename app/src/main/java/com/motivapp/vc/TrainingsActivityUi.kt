package com.motivapp.vc

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.motivapp.R
import com.motivapp.component.ActionRelativeLayout
import com.motivapp.component.actionRelativeLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.design.floatingActionButton

class TrainingsActivityUi : AnkoComponent<TrainingsActivity> {

    lateinit var actionRelativeLayout: ActionRelativeLayout
    lateinit var trainingTextView: TextView
    lateinit var searchCardView: CardView
    lateinit var searchImageView: ImageView
    lateinit var searchTextView: TextView
    lateinit var trainingsListView: ListView
    lateinit var addFloatingActionButton: FloatingActionButton

    override fun createView(ui: AnkoContext<TrainingsActivity>) = with(ui) {
        relativeLayout {
            backgroundColor = Color.WHITE

            actionRelativeLayout = actionRelativeLayout {
                ankoContext = ui
            }

            trainingsListView = listView {
                id = View.generateViewId()
                isVerticalScrollBarEnabled = false
                dividerHeight = dip(.5f)
                divider = ContextCompat.getDrawable(context, R.drawable.list_divider_start_10_end_10)
                setHeaderDividersEnabled(false)
                setFooterDividersEnabled(false)
                overscrollHeader = ContextCompat.getDrawable(context, android.R.color.transparent)
                overscrollFooter = ContextCompat.getDrawable(context, android.R.color.transparent)
                selectorResource = android.R.color.transparent

                addHeaderView(with(AnkoContext.create(context, false)) {
                    relativeLayout {

                        trainingTextView = textView(R.string.trainings_trainings) {
                            id = View.generateViewId()
                            textColor = ContextCompat.getColor(context, R.color.colorBlack)
                            textSize = 32f
                            setTypeface(typeface, Typeface.BOLD)
                        }.lparams {
                            topMargin = dip(10)
                            marginStart = dip(10)
                        }

                        searchCardView = cardView {
                            id = View.generateViewId()
                            cardElevation = dip(1).toFloat()

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER_VERTICAL

                                searchImageView = imageView(R.drawable.ic_search) {
                                    id = View.generateViewId()
                                    setColorFilter(ContextCompat.getColor(context, R.color.colorGray))
                                }.lparams(dip(24), dip(24)) {
                                    margin = dip(5)
                                }

                                searchTextView = textView(R.string.trainings_search) {
                                    id = View.generateViewId()
                                    textColor = ContextCompat.getColor(context, R.color.colorGray)
                                    textSize = 16f
                                }

                            }

                        }.lparams(matchParent, wrapContent) {
                            below(trainingTextView)
                            margin = dip(10)
                        }

                    }
                })

                addFooterView(with(AnkoContext.create(context, false)) {
                    relativeLayout {

                        view().lparams(matchParent, dip(70))

                    }
                })

            }.lparams {
                below(actionRelativeLayout)
            }

            addFloatingActionButton = floatingActionButton {
                id = View.generateViewId()
                imageResource = R.drawable.ic_add_white
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
            }.lparams {
                alignParentEnd()
                alignParentBottom()
                marginEnd = dip(15)
                bottomMargin = dip(15)
            }

        }
    }

}