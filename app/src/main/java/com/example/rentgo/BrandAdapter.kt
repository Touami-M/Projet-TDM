package com.example.rentgo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentgo.databinding.BrandLayoutBinding

class BrandAdapter (val context: Context, var brands:List<Brand>):
    RecyclerView.Adapter<ViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        return ViewHolder2(BrandLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {


        holder.binding.apply {
            brandImage.setImageResource(brands.get(position).brandImage)
            brandName.text = brands[position].brandName
        }
    }

    override fun getItemCount() = brands.size
}

class ViewHolder2(val binding: BrandLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

}