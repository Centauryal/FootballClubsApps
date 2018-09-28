package com.centaury.footballclubsapps.ui.view.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.centaury.footballclubsapps.R
import com.centaury.footballclubsapps.data.model.Club
import org.jetbrains.anko.*

class DetailClubActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var imageClubView: ImageView
    private lateinit var descView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            imageClubView = imageView()
                    .lparams(width = 200, height = 200){
                        bottomMargin = dip(10)
                    }
            nameTextView = textView()
                    .lparams(){
                        gravity = Gravity.CENTER
                        bottomMargin = dip(10)
                    }
            descView = textView()
            padding = dip(16)
            gravity = Gravity.CENTER
            orientation = LinearLayout.VERTICAL

            val team = intent.extras.getParcelable<Club>("clubitem")
            nameTextView.text = team.name
            Glide.with(view().context).load(team.image).into(imageClubView)
            descView.text = team.desc
        }
    }
}
