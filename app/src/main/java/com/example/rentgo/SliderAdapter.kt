package com.example.rentgo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(imageUrl: ArrayList<String>) :
    SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    var sliderList: ArrayList<String> = imageUrl

    override fun getCount(): Int {
        return sliderList.size
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        val inflate: View =
            LayoutInflater.from(parent!!.context).inflate(R.layout.slider_item, null)

        return SliderViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {

        if (viewHolder != null) {
            Glide.with(viewHolder.itemView).load(url+sliderList[position]).fitCenter()
                .into(viewHolder.imageView)
        }
    }

    class SliderViewHolder(itemView: View?) : ViewHolder(itemView) {

        var imageView: ImageView = itemView!!.findViewById(R.id.image_view)
    }
}
