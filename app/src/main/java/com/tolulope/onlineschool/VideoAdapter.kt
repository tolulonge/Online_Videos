package com.tolulope.onlineschool

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(data:ArrayList<VideoView>, var context: Context) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

     var data:List<VideoView> = data


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item){

        var vTitle:TextView = item.findViewById(R.id.videoTitle)
        var vDesc:TextView = item.findViewById(R.id.videoDesc)
        var btnDownload: Button = item.findViewById(R.id.btnDownload)
        var video:tcking.github.com.giraffeplayer2.VideoView = item.findViewById(R.id.videoView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vTitle.text = data[position].videoTitle
        holder.vDesc.text = data[position].videoDesc
        holder.video.setVideoPath(data[position].videoUrl)
        holder.btnDownload.setOnClickListener {

            val download = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val videoUri = Uri.parse(data[position].videoUrl)
            val getVideo = DownloadManager.Request(videoUri)
            getVideo.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            download.enqueue(getVideo)


        }
    }
}