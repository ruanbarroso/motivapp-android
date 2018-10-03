package com.motivapp.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.motivapp.model.Training
import org.jetbrains.anko.AnkoContext

class TrainingAdapter(context: Context, trainings: ArrayList<Training>) : ArrayAdapter<Training>(context, 0, trainings) {

    private val context = AnkoContext.createReusable(getContext(), this)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view = convertView ?: TrainingAdapterUi().createView(context)

        val training = getItem(position)!!


        return view
    }

}