package com.tolulope.onlineschool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val video1 = "https://firebasestorage.googleapis.com/v0/b/online-school-1d680.appspot.com/o/jumpLunge.mp4?alt=media&token=ef1eb1f1-bf0f-4835-b816-6152e772618a"
        val video2 = "https://firebasestorage.googleapis.com/v0/b/online-school-1d680.appspot.com/o/planks.mp4?alt=media&token=39ae4902-7a45-4155-b610-036c52e4915a"
        val video3 = "https://firebasestorage.googleapis.com/v0/b/online-school-1d680.appspot.com/o/pullUps.mp4?alt=media&token=5211d0b5-4889-45e1-8c60-d2d3b4df81ee"
        val video4 = "https://firebasestorage.googleapis.com/v0/b/online-school-1d680.appspot.com/o/pushUp.mp4?alt=media&token=74c6dddd-f7e6-4682-9242-7cce31565205"
        val video5 = "https://firebasestorage.googleapis.com/v0/b/online-school-1d680.appspot.com/o/sitUp.mp4?alt=media&token=91867981-7c76-45e5-a9c6-b52b171198a1"

        val items = ArrayList<VideoView>()
        items.add(VideoView(video1, "Jump Lunges", "Develop and improve lower body strength and power"))
        items.add(VideoView(video2, "Planks", "Strengthens your core and improve balance"))
        items.add(VideoView(video3, "Pull Ups", "Strengthens arm and shoulder muscles"))
        items.add(VideoView(video4, "Push Ups", "Building upper body strength"))
        items.add(VideoView(video5, "Sit Ups", "For good posture and core stabilizing"))

        val videoRecycler = findViewById<RecyclerView>(R.id.recyclerVideo)
        val adapter = VideoAdapter(items, applicationContext)

        videoRecycler.layoutManager = GridLayoutManager(applicationContext, 1)

        videoRecycler.adapter = adapter


    }
}