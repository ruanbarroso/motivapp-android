package com.motivapp.vc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.setContentView

class SearchActivity : AppCompatActivity() {

    private lateinit var ui: SearchActivityUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region <! Carregando o Layout !>
        ui = SearchActivityUi()
        ui.setContentView(this)
        //endregion

    }

}