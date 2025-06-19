package com.example.playlistmanagerapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PlaylistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        val titles = intent.getStringArrayListExtra("titles") ?: arrayListOf()
        val artists = intent.getStringArrayListExtra("artists") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()
        val ratingsArray = intent.getIntArrayExtra("ratings") ?: intArrayOf()

        val songListView = findViewById<TextView>(R.id.songList)
        val avgRatingView = findViewById<TextView>(R.id.avgRating)
        val backButton = findViewById<Button>(R.id.btnBack)

        val builder = StringBuilder()
        for (i in titles.indices) {
            builder.append("Song ${i + 1}:\n")
            builder.append("Title: ${titles[i]}\n")
            builder.append("Artist: ${artists[i]}\n")
            builder.append("Rating: ${ratingsArray[i]}\n")
            builder.append("Comment: ${comments[i]}\n\n")
        }

        songListView.text = builder.toString()

        val avgRating = if (ratingsArray.isNotEmpty()) ratingsArray.average() else 0.0
        avgRatingView.text = "Average Rating: %.2f".format(avgRating)

        backButton.setOnClickListener {
            finish()
        }
    }
}
