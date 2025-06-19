package com.example.playlistmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val songTitles = ArrayList<String>()
    private val artistNames = ArrayList<String>()
    private val ratings = ArrayList<Int>()
    private val comments = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleInput = findViewById<EditText>(R.id.editTitle)
        val artistInput = findViewById<EditText>(R.id.editArtist)
        val ratingInput = findViewById<EditText>(R.id.editRating)
        val commentInput = findViewById<EditText>(R.id.editComment)

        val addButton = findViewById<Button>(R.id.btnAdd)
        val viewButton = findViewById<Button>(R.id.btnView)
        val exitButton = findViewById<Button>(R.id.btnExit)

        addButton.setOnClickListener {
            val title = titleInput.text.toString()
            val artist = artistInput.text.toString()
            val ratingText = ratingInput.text.toString()
            val comment = commentInput.text.toString()

            if (title.isBlank() || artist.isBlank() || ratingText.isBlank() || comment.isBlank()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val rating = ratingText.toIntOrNull()
            if (rating == null || rating !in 1..5) {
                Toast.makeText(this, "Rating must be between 1 and 5.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (songTitles.size >= 4) {
                Toast.makeText(this, "Maximum of 4 songs allowed.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            songTitles.add(title)
            artistNames.add(artist)
            ratings.add(rating)
            comments.add(comment)

            titleInput.text.clear()
            artistInput.text.clear()
            ratingInput.text.clear()
            commentInput.text.clear()

            Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()
        }

        viewButton.setOnClickListener {
            val intent = Intent(this, PlaylistActivity::class.java)
            intent.putExtra("titles", songTitles)
            intent.putExtra("artists", artistNames)
            intent.putExtra("ratings", ratings.toIntArray())
            intent.putExtra("comments", comments)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}
